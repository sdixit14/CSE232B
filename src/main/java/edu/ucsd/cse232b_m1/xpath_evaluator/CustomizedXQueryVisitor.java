package edu.ucsd.cse232b_m1.xpath_evaluator;

import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.ucsd.cse232b_m1.parsers.XQueryGrammarBaseVisitor;
import edu.ucsd.cse232b_m1.parsers.XQueryGrammarParser;

import java.util.LinkedList;

public class CustomizedXQueryVisitor extends XQueryGrammarBaseVisitor<LinkedList>{
    private HashMap<String, LinkedList<Node>> contextMap = new HashMap<>();
    private Stack<HashMap<String, LinkedList<Node>>> contextStack = new Stack<>();
    Document outputDoc = null;
    private Document doc = null;

    public CustomizedXQueryVisitor(){
        try {
            DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder docB = docBF.newDocumentBuilder();
            outputDoc = docB.newDocument();
            doc = docB.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    @Override public LinkedList<Node> visitFLWR(XQueryGrammarParser.FLWRContext ctx) {
        LinkedList<Node> results = new LinkedList<>();
        HashMap<String, LinkedList<Node>> contextMapOld = new HashMap<>(contextMap);
        contextStack.push(contextMapOld);


        FLWRHelper(0, results, ctx);

        contextMap = contextStack.pop();
        return results;
    }


    private void FLWRHelper(int k, LinkedList<Node> results, XQueryGrammarParser.FLWRContext ctx){
        int numFor;
        numFor = ctx.forClause().var().size();
        if (k == numFor){
            if (ctx.letClause() != null) visit(ctx.letClause());
            if (ctx.whereClause() != null)
               if (visit(ctx.whereClause()).size() == 0) return;
            LinkedList<Node> result = visit(ctx.returnClause());
            results.addAll(result);
        }
        else{
            String key = ctx.forClause().var(k).getText();
            LinkedList<Node> valueList = visit(ctx.forClause().xq(k));

            for (Node node: valueList){
                HashMap<String, LinkedList<Node>> contextMapOld = new HashMap<>(contextMap);
                contextStack.push(contextMapOld);

                LinkedList<Node> value = new LinkedList<>(); value.add(node);
                contextMap.put(key, value);
                if (k+1 <= numFor)
                    FLWRHelper(k+1, results, ctx);
                contextMap = contextStack.pop();
            }
        }
    }
    @Override public LinkedList<Node> visitTagXQ(XQueryGrammarParser.TagXQContext ctx) {

        String tagName = ctx.startTag().tagName().getText();
        LinkedList<Node> nodeList = visit(ctx.xq());
        Node node = makeElem(tagName, nodeList);
        LinkedList<Node> result = new  LinkedList<>();
        result.add(node);
        return result;
    }

    @Override public LinkedList<Node> visitApXQ(XQueryGrammarParser.ApXQContext ctx) {
        String ap = ctx.getText();
        LinkedList<Node> results = XPath.evalAp(ap);
        return results;
    }

    @Override public LinkedList<Node> visitLetXQ(XQueryGrammarParser.LetXQContext ctx) {
        HashMap<String, LinkedList<Node>> contextMapOld = new HashMap<>(contextMap);
        contextStack.push(contextMapOld);
        LinkedList<Node> result = visitChildren(ctx);
        contextMap = contextStack.pop();
        return result;

    }

    @Override public LinkedList<Node> visitCommaXQ(XQueryGrammarParser.CommaXQContext ctx) {
        LinkedList<Node> result = visit(ctx.xq(0));
        result.addAll(visit(ctx.xq(1)));
        return result;
    }

    @Override public LinkedList<Node> visitVarXQ(XQueryGrammarParser.VarXQContext ctx) {
        return contextMap.get(ctx.getText());
    }

    @Override public LinkedList<Node> visitScXQ(XQueryGrammarParser.ScXQContext ctx) {
        String str = ctx.StringConstant().getText();
        int len = str.length();
        str = str.substring(1,len-1);
        Node node = makeText(str);
        LinkedList<Node> result = new LinkedList<>();
        result.add(node);
        return result;
    }

    @Override public LinkedList<Node> visitBraceXQ(XQueryGrammarParser.BraceXQContext ctx) {
        return visit(ctx.xq());
    }

    @Override public LinkedList<Node> visitSingleSlashXQ(XQueryGrammarParser.SingleSlashXQContext ctx) {
        LinkedList<Node> currentNodes = new LinkedList<>();
        copyOf(visit(ctx.xq()), currentNodes);
        LinkedList<Node> results = XPath.evalRp(currentNodes, ctx.rp().getText());
        return results;
    }

    @Override public LinkedList<Node> visitDoubleSlashXQ(XQueryGrammarParser.DoubleSlashXQContext ctx) {
        LinkedList<Node> currentNodes = new LinkedList<>();
        copyOf(visit(ctx.xq()), currentNodes);
        LinkedList<Node> descendants = getDescendants(currentNodes);
        currentNodes.addAll(descendants);
        LinkedList<Node> results = XPath.evalRp(currentNodes, ctx.rp().getText());
        return results;
    }

    private void copyOf(LinkedList<Node> l1, LinkedList<Node> l2){
        for (Node node : l1)
            l2.add(node);
    }

    @Override public LinkedList<Node> visitForClause(XQueryGrammarParser.ForClauseContext ctx) {
        //forClauseHelper(0, ctx);
        return null;
    }

    @Override public LinkedList<Node> visitLetClause(XQueryGrammarParser.LetClauseContext ctx) {
        for (int i = 0; i < ctx.var().size(); i++) {
            String key = ctx.var(i).getText();
            LinkedList<Node> value = visit(ctx.xq(i));
            contextMap.put(key, value);
        }
        return null;
    }

    @Override public LinkedList<Node> visitWhereClause(XQueryGrammarParser.WhereClauseContext ctx) {
        return visit(ctx.cond());
    }

    @Override public LinkedList<Node> visitReturnClause(XQueryGrammarParser.ReturnClauseContext ctx) {
        return visit(ctx.xq());
    }

    @Override public LinkedList<Node> visitBraceCond(XQueryGrammarParser.BraceCondContext ctx) {
        return visit(ctx.cond());
    }



    private boolean satisfyCondHelper(int k, XQueryGrammarParser.SatisfyCondContext ctx){

        int numFor = ctx.var().size();
        if (k == numFor){
            if (visit(ctx.cond()).size() == 1)
                return true;
        }
        else{
            String key = ctx.var(k).getText();
            LinkedList<Node> valueList = visit(ctx.xq(k));

            for (Node node: valueList){
                HashMap<String, LinkedList<Node>> contextMapOld = new HashMap<>(contextMap);
                contextStack.push(contextMapOld);

                LinkedList<Node> value = new LinkedList<>(); value.add(node);
                contextMap.put(key, value);
                if (k+1 <= numFor)
                    if (satisfyCondHelper(k+1, ctx)) {
                    contextMap = contextStack.pop();
                    return true;
                }
                contextMap = contextStack.pop();
            }
        }
        return false;
    }


    @Override public LinkedList<Node> visitSatisfyCond(XQueryGrammarParser.SatisfyCondContext ctx) {
        LinkedList<Node> result = new LinkedList<>();
        if (satisfyCondHelper(0, ctx)){
            Node True = doc.createTextNode("true");
            result.add(True);
        }
        return result;
    }

    @Override public LinkedList<Node> visitEmptyCond(XQueryGrammarParser.EmptyCondContext ctx) {
        LinkedList<Node> xqResult = visit(ctx.xq());
        LinkedList<Node> result = new LinkedList<>();
        if (xqResult.isEmpty()){
            Node True = doc.createTextNode("true");
            result.add(True);
        }
        return result;
    }

    @Override public LinkedList<Node> visitOrCond(XQueryGrammarParser.OrCondContext ctx) {
        LinkedList<Node> left = new LinkedList<>(visit(ctx.cond(0)));
        LinkedList<Node> right = new LinkedList<>(visit(ctx.cond(1)));
        LinkedList<Node> result = new LinkedList<>();
        if (left.size() > 0 || right.size() > 0){
            Node True = doc.createTextNode("true");
            result.add(True);
        }
        return result;
    }

    @Override public LinkedList<Node> visitAndCond(XQueryGrammarParser.AndCondContext ctx) {
        LinkedList<Node> left = new LinkedList<>(visit(ctx.cond(0)));
        LinkedList<Node> right = new LinkedList<>(visit(ctx.cond(1)));
        LinkedList<Node> result = new LinkedList<>();
        if (left.size() > 0 && right.size() > 0){
            Node True = doc.createTextNode("true");
            result.add(True);
        }
        return result;
    }

    @Override public LinkedList<Node> visitIsCond(XQueryGrammarParser.IsCondContext ctx) {
        LinkedList<Node> left = new LinkedList<>(visit(ctx.xq(0)));
        LinkedList<Node> right = new LinkedList<>(visit(ctx.xq(1)));
        LinkedList<Node> result = new LinkedList<>();
        for (Node l: left)
            for (Node r: right)
                if (l.isSameNode(r)){
                    Node True = doc.createTextNode("true");
                    result.add(True);
                    return result;
                }
        return result;
    }


    @Override public LinkedList<Node> visitEqCond(XQueryGrammarParser.EqCondContext ctx) {
        LinkedList<Node> left = new LinkedList<>(visit(ctx.xq(0)));
        LinkedList<Node> right = new LinkedList<>(visit(ctx.xq(1)));
        LinkedList<Node> result = new LinkedList<>();
        for (Node l: left)
            for (Node r: right)
                if (l.isEqualNode(r)){
                    Node True = doc.createTextNode("true");
                    result.add(True);
                    return result;
                }
        return result;
    }

    @Override public LinkedList<Node> visitNotCond(XQueryGrammarParser.NotCondContext ctx) {
        LinkedList<Node> oppResult = new LinkedList<>(visit(ctx.cond()));
        LinkedList<Node> result = new LinkedList<>();
        if (oppResult.isEmpty()){
            Node True = doc.createTextNode("true");
            result.add(True);
        }
        return result;
    }

    @Override public LinkedList<Node> visitStartTag(XQueryGrammarParser.StartTagContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitEndTag(XQueryGrammarParser.EndTagContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitVar(XQueryGrammarParser.VarContext ctx) {
        return visitChildren(ctx);
    }

    private Node makeText(String s){
        Node result = doc.createTextNode(s);
        return result;
    }
    private Node makeElem(String tag, LinkedList<Node> list){
        Node result = outputDoc.createElement(tag);
        for (Node node : list) {
            if (node != null) {
                Node newNode = outputDoc.importNode(node, true);
                result.appendChild(newNode);
            }
        }
        return result;
    }
    //from XPath

    @Override public LinkedList<Node> visitDoubleAP(XQueryGrammarParser.DoubleAPContext ctx) {return visitChildren(ctx);}

    @Override public LinkedList<Node> visitSingleAP(XQueryGrammarParser.SingleAPContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitDoc(XQueryGrammarParser.DocContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitBraceRP(XQueryGrammarParser.BraceRPContext ctx) { return visitChildren(ctx);}

    @Override public LinkedList<Node> visitDoubleSlashRP(XQueryGrammarParser.DoubleSlashRPContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitTextRP(XQueryGrammarParser.TextRPContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitAttRP(XQueryGrammarParser.AttRPContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitParentRP(XQueryGrammarParser.ParentRPContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitSelfRP(XQueryGrammarParser.SelfRPContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitFilterRP(XQueryGrammarParser.FilterRPContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitCommaRP(XQueryGrammarParser.CommaRPContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitChildrenRP(XQueryGrammarParser.ChildrenRPContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitTagRP(XQueryGrammarParser.TagRPContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitSingleSlashRP(XQueryGrammarParser.SingleSlashRPContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitEqFilter(XQueryGrammarParser.EqFilterContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitNotFilter(XQueryGrammarParser.NotFilterContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitAndFilter(XQueryGrammarParser.AndFilterContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitIsFilter(XQueryGrammarParser.IsFilterContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitRpFilter(XQueryGrammarParser.RpFilterContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitBraceFilter(XQueryGrammarParser.BraceFilterContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitOrFilter(XQueryGrammarParser.OrFilterContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitTagName(XQueryGrammarParser.TagNameContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitAttName(XQueryGrammarParser.AttNameContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitFilename(XQueryGrammarParser.FilenameContext ctx) { return visitChildren(ctx); }


    public static LinkedList<Node> getChildren(LinkedList<Node> n){
        /**
         * return the children of the a node (just the next level)
         */
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