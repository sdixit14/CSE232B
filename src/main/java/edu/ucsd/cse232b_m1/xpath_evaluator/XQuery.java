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
        	String inputFilename = "xquery_m3_input.txt";
            String outputFilename = "xquery_m3_result.xml";
              
            long start_time = System.currentTimeMillis();
            System.out.println("Start time in miliseconds: "+ start_time);
            
            CharStream codePointCharStream = CharStreams.fromFileName(inputFilename);
            XQueryGrammarLexer lexer = new XQueryGrammarLexer(codePointCharStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XQueryGrammarParser parser = new XQueryGrammarParser(tokens);
            parser.removeErrorListeners();
            ParseTree tree = parser.xq();

            CustomizedXQueryVisitor visitor = new CustomizedXQueryVisitor();
            LinkedList<Node> results = visitor.visit(tree);
            LinkedList<Node> finalResult;
            if (results.size() == 1) {
                finalResult = results;
            }
            else{
                finalResult = makeElem(visitor.outputDoc, "result", results);
            }
            writeToFile(visitor.outputDoc, finalResult, outputFilename);
            
            long end_time = System.currentTimeMillis(); 
            System.out.println("End time in miliseconds: "+ end_time);
            
            System.out.println("Total Elapsed Time: "+ (end_time-start_time));
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static LinkedList evalRewrited(String rewrited) {
        try {
        	CharStream codePointCharStream = CharStreams.fromString(rewrited);
            XQueryGrammarLexer lexer = new XQueryGrammarLexer(codePointCharStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XQueryGrammarParser parser = new XQueryGrammarParser(tokens);
            parser.removeErrorListeners();
            ParseTree tree = parser.xq();

            //for Visitor
            CustomizedXQueryVisitor visitor = new CustomizedXQueryVisitor();
            visitor.needRewrite = false;
            LinkedList<Node> results = visitor.visit(tree);
            return results;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
        return null;

    }

    public static void writeToFile(Document doc, LinkedList<Node> result, String filePath) {

        doc.appendChild(doc.importNode(result.get(0), true));

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
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