package edu.ucsd.cse232b_m1.xpath_evaluator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.Node;

import edu.ucsd.cse232b_m1.parsers.XQueryGrammarLexer;
import edu.ucsd.cse232b_m1.parsers.XQueryGrammarParser;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import org.w3c.dom.Document;
import java.io.*;
import java.util.LinkedList;

public class XQuery {

    public static void main(String[] args) throws IOException {
        try{
        	String inputFilename = args[0];
            String outputFilename = args[1];
              
            CharStream codePointCharStream = CharStreams.fromFileName(inputFilename);
            XQueryGrammarLexer lexer = new XQueryGrammarLexer(codePointCharStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XQueryGrammarParser parser = new XQueryGrammarParser(tokens);
            parser.removeErrorListeners();
            ParseTree tree = parser.xq();

            //for Visitor
            CustomizedXQueryVisitor visitor = new CustomizedXQueryVisitor();
            LinkedList<Node> results = visitor.visit(tree);
            LinkedList<Node> finalResult;
            if (results.size() == 1) {
                System.out.println(results.get(0).getChildNodes().getLength());
                finalResult = results;
            }
            else{
                System.out.println(results.size());
                finalResult = makeElem(visitor.outputDoc, "result", results);
            }
            writeToFile(visitor.outputDoc, finalResult, outputFilename);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }


    public static void writeToFile(Document doc, LinkedList<Node> result, String filePath) {
        doc.appendChild(result.get(0));
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult res = new StreamResult(filePath);
            transformer.transform(source, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static LinkedList<Node> makeElem(Document doc, String tag, LinkedList<Node> list){
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

}