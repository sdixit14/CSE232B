package edu.ucsd.cse232b_m1.xpath_evaluator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.IOException;
import java.util.*;

import edu.ucsd.cse232b_m1.parsers.XPathGrammarLexer;
import edu.ucsd.cse232b_m1.parsers.XPathGrammarParser;

public class XPath {
	public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException {
        String inputFilename = args[0];
        String outputFilename = args[1];
        Document outputDoc = null;
        
        CharStream codePointCharStream = CharStreams.fromFileName(inputFilename);
        XPathGrammarLexer lexer = new XPathGrammarLexer(codePointCharStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        XPathGrammarParser parser = new XPathGrammarParser(tokens);
        ParseTree tree = parser.ap();

        CustomizedXPathVisitor visitor = new CustomizedXPathVisitor();
        LinkedList<Node> results = visitor.visit(tree);

        DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
        DocumentBuilder docB = docBF.newDocumentBuilder();
        outputDoc = docB.newDocument();
        LinkedList<Node> finalResult = createElements(visitor.inputDoc, "RESULT", results);
        writeToFile(outputDoc, finalResult, outputFilename);
    }

    public static void writeToFile(Document doc, LinkedList<Node> result, String filePath) throws TransformerException {
        Node newNode = doc.importNode(result.get(0), true);
        doc.appendChild(newNode);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult res = new StreamResult(filePath);
        transformer.transform(source, res);
    }
    
    private static LinkedList<Node> createElements(Document doc, String tag, LinkedList<Node> list){
        Node result = doc.createElement(tag);
        for (Node node : list) {
            if (node != null) {
                Node newNode = doc.importNode(node, true);
                result.appendChild(newNode);
            }
        }
        LinkedList<Node> results = new LinkedList<>();
        results.add(result);
        return results;
    }
    public static LinkedList evalAp(String ap) {
        try {
        	CharStream codePointCharStream = CharStreams.fromString(ap);
            XPathGrammarLexer lexer = new XPathGrammarLexer(codePointCharStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XPathGrammarParser parser = new XPathGrammarParser(tokens);
            parser.removeErrorListeners();
            ParseTree tree = parser.ap();

            //for Visitor
            CustomizedXPathVisitor visitor = new CustomizedXPathVisitor();
            LinkedList<Node> results = visitor.visit(tree);
            return results;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
        return null;

    }

    public static LinkedList evalRp(LinkedList<Node> currentNodes, String rp) {
        try {
        	CharStream codePointCharStream = CharStreams.fromString(rp);
            XPathGrammarLexer lexer = new XPathGrammarLexer(codePointCharStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XPathGrammarParser parser = new XPathGrammarParser(tokens);
            parser.removeErrorListeners();
            ParseTree tree = parser.rp();

            //for Visitor
            CustomizedXPathVisitor visitor = new CustomizedXPathVisitor();
            visitor.setCurrentNodes(currentNodes);
            LinkedList<Node> results = visitor.visit(tree);
            return results;


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }

        return null;
    }
}