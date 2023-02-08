package edu.ucsd.cse232b_m1.xpath_evaluator;

import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.ucsd.cse232b_m1.parsers.XPathGrammarBaseVisitor;
import edu.ucsd.cse232b_m1.parsers.XPathGrammarParser;


public class CustomizedXPathVisitor extends XPathGrammarBaseVisitor<LinkedList> {
    LinkedList<Node> currentNodes = new LinkedList<Node>();
    Document inputDoc= null;


    public void setCurrentNodes(LinkedList<Node> Nodes){
        currentNodes = Nodes;
    }
    
    @Override
    public LinkedList<Node> visitDoc(XPathGrammarParser.DocContext ctx){
        File xml = new File(ctx.filename().getText());
        DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
        docBF.setIgnoringElementContentWhitespace(true);
        DocumentBuilder docB = null;
        LinkedList<Node> results = new LinkedList<>();
        try {
            docB = docBF.newDocumentBuilder();
        } catch (ParserConfigurationException pE1) {
            pE1.printStackTrace();
        }
        try {
            if (docB != null) {
               inputDoc= docB.parse(xml);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (inputDoc != null) {
            inputDoc.getDocumentElement().normalize();
        }
        results.add(inputDoc);
        setCurrentNodes(results);
        return results;
    }


    @Override public LinkedList<Node> visitDoubleAP(XPathGrammarParser.DoubleAPContext ctx) {
        visit(ctx.doc());
        LinkedList<Node> descendants = getDescendants(currentNodes);
        currentNodes.addAll(descendants);
        return visit(ctx.rp());
    }

    @Override public LinkedList<Node> visitSingleAP(XPathGrammarParser.SingleAPContext ctx) {
        visit(ctx.doc());
        return visit(ctx.rp());
    }

    @Override public LinkedList<Node> visitChildrenRP(XPathGrammarParser.ChildrenRPContext ctx) {
        LinkedList<Node> results = getChildren(currentNodes);
        setCurrentNodes(results);
        return results;
    }

    @Override public LinkedList<Node> visitBraceRP(XPathGrammarParser.BraceRPContext ctx) { 
    	return visit(ctx.rp()); 
    }

    @Override public LinkedList<Node> visitSingleSlashRP(XPathGrammarParser.SingleSlashRPContext ctx) {
        visit(ctx.rp(0));
        return visit(ctx.rp(1));
    }

    @Override public LinkedList<Node> visitDoubleSlashRP(XPathGrammarParser.DoubleSlashRPContext ctx) {
        visit(ctx.rp(0));
        LinkedList<Node> descendants = getDescendants(currentNodes);
        currentNodes.addAll(descendants);
        return visit(ctx.rp(1));
    }

    @Override public LinkedList<Node> visitTextRP(XPathGrammarParser.TextRPContext ctx) {
         LinkedList<Node> results = getChildren(currentNodes);
         setCurrentNodes(results);
         return currentNodes;
    }

    @Override public LinkedList<Node> visitAttRP(XPathGrammarParser.AttRPContext ctx) {
        LinkedList<Node> results= new LinkedList<Node>();
        String attName = ctx.attName().getText();
        for (Node node : currentNodes)
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                String attValue = e.getAttribute(attName);
                if (!attValue.equals("")) {
                    results.add(node);
                }
            }
        
        setCurrentNodes(results);
        return results;
    }

    @Override public LinkedList<Node> visitParentRP(XPathGrammarParser.ParentRPContext ctx) {
    	LinkedList<Node> results = getParents(currentNodes);
    	setCurrentNodes(results);
        return results;
    }

    @Override public LinkedList<Node> visitSelfRP(XPathGrammarParser.SelfRPContext ctx) { 
    	return currentNodes; 
    }

    @Override public LinkedList<Node> visitCommaRP(XPathGrammarParser.CommaRPContext ctx) {
        LinkedList<Node> results;
        LinkedList<Node> oldCurrent = currentNodes;
        visit(ctx.rp(0));
        LinkedList<Node> resultsLeft = currentNodes;
        setCurrentNodes(oldCurrent);
        visit(ctx.rp(1));
        LinkedList<Node> resultsRight = currentNodes;
        resultsLeft.addAll(resultsRight);
        results = resultsLeft;
        setCurrentNodes(results);
        return results;}

    @Override public LinkedList<Node> visitTagRP(XPathGrammarParser.TagRPContext ctx) {
        LinkedList<Node> results = new LinkedList<Node>();

        LinkedList<Node> childrenList = getChildren(currentNodes);
        for (Node child: childrenList)
            if(child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals(ctx.getText()))
                results.add(child);

        setCurrentNodes(results);
        return results;
    }


    @Override public LinkedList<Node> visitFilterRP(XPathGrammarParser.FilterRPContext ctx) {
        visit(ctx.rp());
        currentNodes = visit(ctx.f());
        return currentNodes;
    }

    @Override public LinkedList<Node> visitRpFilter(XPathGrammarParser.RpFilterContext ctx) {
        LinkedList<Node> keepCurrent = currentNodes;
        LinkedList<Node> results = new LinkedList<>();
        for (Node node : keepCurrent){
            LinkedList<Node> newCurrent = new LinkedList<>();
            newCurrent.add(node);
            currentNodes = newCurrent;
            if (visit(ctx.rp()).size() > 0)
                results.add(node);
        }
        setCurrentNodes(results);
        return results;
    }

    @Override public LinkedList<Node> visitNotFilter(XPathGrammarParser.NotFilterContext ctx) {
        HashSet<Node> leftSet = new HashSet<Node>(currentNodes);
        HashSet<Node> rightSet = new HashSet<Node>(visit(ctx.f()));
        HashSet<Node> difference = new HashSet<Node>();
        difference.addAll(leftSet);
        difference.removeAll(rightSet);

        LinkedList<Node> results = new LinkedList<Node>(difference);
        return results; 
    }

    @Override public LinkedList<Node> visitAndFilter(XPathGrammarParser.AndFilterContext ctx) {
        HashSet<Node> leftSet = new HashSet<Node>(visit(ctx.f(0)));
        HashSet<Node> rightSet = new HashSet<Node>(visit(ctx.f(1)));
        HashSet<Node> intersection = new HashSet<Node>();
        intersection.addAll(leftSet);
        intersection.retainAll(rightSet);

        LinkedList<Node> results = new LinkedList<Node>(intersection);
        return results;
    }

    @Override public LinkedList<Node> visitOrFilter(XPathGrammarParser.OrFilterContext ctx) {
        HashSet<Node> leftSet = new HashSet<Node>(visit(ctx.f(0)));
        HashSet<Node> rightSet = new HashSet<Node>(visit(ctx.f(1)));
        HashSet<Node> union = new HashSet<Node>();
        union.addAll(leftSet);
        union.addAll(rightSet);
        
        LinkedList<Node> results = new LinkedList<Node>(union);
        return results;
    }

    @Override public LinkedList<Node> visitIsFilter(XPathGrammarParser.IsFilterContext ctx) {
        LinkedList<Node> current = currentNodes;
        LinkedList<Node> results = new LinkedList<>();

        for (Node node : current){
            LinkedList<Node> single = new LinkedList<>();
            single.add(node);
            setCurrentNodes(single);

            LinkedList<Node> left = visit(ctx.rp(0));
            setCurrentNodes(single);
            LinkedList<Node> right = visit(ctx.rp(1));
            for (Node l : left)
                for (Node r: right)
                    if (l.isSameNode(r) && !results.contains(node))
                        results.add(node);
        }
        setCurrentNodes(results);
        return results;
    }
    
    @Override 
    public LinkedList<Node> visitEqFilter(XPathGrammarParser.EqFilterContext ctx) {
        LinkedList<Node> current = currentNodes;
        LinkedList<Node> results = new LinkedList<>();

        for (Node node : current){
            LinkedList<Node> single = new LinkedList<>();
            single.add(node);
            setCurrentNodes(single);

            LinkedList<Node> left = visit(ctx.rp(0));
            setCurrentNodes(single);
            LinkedList<Node> right = visit(ctx.rp(1));
            for (Node l : left)
                for (Node r: right)
                    if (l.isEqualNode(r) && !results.contains(node))
                        results.add(node);
        }
        setCurrentNodes(results);
        return results;
    }

    @Override 
    public LinkedList<Node> visitBraceFilter(XPathGrammarParser.BraceFilterContext ctx) { 
    	return visit(ctx.f()); 
    }

    @Override 
    public LinkedList<Node> visitTagName(XPathGrammarParser.TagNameContext ctx) { 
    	return visitChildren(ctx); 
    }

    @Override 
    public LinkedList<Node> visitAttName(XPathGrammarParser.AttNameContext ctx) { 
    	return visitChildren(ctx); 
    }

    public static LinkedList<Node> getChildren(LinkedList<Node> n){
        LinkedList<Node> childrenList = new LinkedList<Node>();
        for(int j = 0; j < n.size(); j++) {
            Node curNode = n.get(j);
            for (int i = 0; i < curNode.getChildNodes().getLength(); i++) {
                childrenList.add(curNode.getChildNodes().item(i));
            }
        }
        return childrenList;
    }

    public LinkedList<Node> getParents(LinkedList<Node> input) {
        LinkedList<Node> res = new LinkedList<Node>();
        for(int i = 0; i < input.size(); i++) {
            Node parentNode = input.get(i).getParentNode();
            if(!res.contains(parentNode)) {
                res.add(parentNode);
            }
        }
        return res;
    }

    public LinkedList<Node> getDescendants(LinkedList<Node> list) {
        LinkedList<Node> desc = new LinkedList<Node>();
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getChildNodes().getLength() != 0) {
                for(int j = 0; j < list.get(i).getChildNodes().getLength(); j++) {
                    desc.addAll(getAllNodes(list.get(i).getChildNodes().item(j)));
                }
            }
        }
        return desc;
    }

    public LinkedList<Node> getAllNodes(Node n) {
        LinkedList<Node> allNodes = new LinkedList<Node>();
        for(int i = 0; i < n.getChildNodes().getLength(); i++) {
            allNodes.addAll( getAllNodes( n.getChildNodes().item(i) ) );
        }
        allNodes.add(n);
        return allNodes;
    }
}