package edu.ucsd.cse232b_m1.xpath_evaluator;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    boolean needRewrite = true;

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
        if (!needRewrite){
            FLWRHelper(0, results, ctx);
        }
        else{
            String rewrited = reWriter(ctx);
            if (rewrited  == ""){
                FLWRHelper(0, results, ctx);
            }
            else
                results = XQuery.evalRewrited(rewrited);
        }
        contextMap = contextStack.pop();
        return results;
    }

    private String reWriter(XQueryGrammarParser.FLWRContext ctx){
        String output = "";
        int numFor;
        numFor = ctx.forClause().var().size();
        List<HashSet<String>> classify = new LinkedList<HashSet<String>>();
        List<String> relation = new LinkedList<String>();
        for(int i=0; i < numFor;i++) {
            String key = ctx.forClause().var(i).getText();
            String parent = ctx.forClause().xq(i).getText().split("/")[0];
            int size = classify.size();
            boolean found = false;
            for(int j = 0; j < size; j++) {
                HashSet<String> curSet = classify.get(j);
                if(curSet.contains(parent)) {
                    curSet.add(key);
                    found = true;
                    break;
                }
            }
            if(!found) {
                HashSet<String> newSet = new HashSet<String>();
                newSet.add(key);
                classify.add(newSet);
                relation.add(key);
            }
        }
        String[] where = ctx.whereClause().cond().getText().split("and");
        String[][] cond = new String[where.length][2];
        for(int i = 0; i < where.length;i++) {
            cond[i][0] = where[i].split("eq|=")[0];
            cond[i][1] = where[i].split("eq|=")[1];
        }
        if(classify.size() == 1) {
            return "";
        }
        int[][] relaWhere = new int[cond.length][2];

        for(int i=0; i < cond.length; i++) {
            String cur0 = cond[i][0];
            String cur1 = cond[i][1];
            relaWhere[i][0] = -1;
            relaWhere[i][1] = -1;
            for(int j = 0; j < classify.size();j++) {
                if(classify.get(j).contains(cur0)) {
                    relaWhere[i][0] = j;
                }
                if(classify.get(j).contains(cur1)) {
                    relaWhere[i][1] = j;
                }
            }
        }
        int class_size = classify.size();
        output += "for $tuple in";
        for (int i = 1; i < class_size;i++) {
                output += " join (";

        }
        output = Print2Join(classify, ctx, output,cond,relaWhere);
        if(class_size > 2) {
            output = Print3Join(classify, ctx, output, cond, relaWhere);
        }
        if(class_size > 3) {
            output = Print4Join(classify, ctx, output, cond, relaWhere);
        }
        if(class_size > 4) {
            output = Print5Join(classify, ctx, output, cond, relaWhere);
        }
        if(class_size > 5) {
            output = Print6Join(classify, ctx, output, cond, relaWhere);
        }
        String retClause = ctx.returnClause().rt().getText();
        String[] tempRet = retClause.split("\\$");
        for (int i = 0; i < tempRet.length-1; i++) {
            tempRet[i] = tempRet[i]+"$tuple/";
        }
        retClause  = tempRet[0];
        for (int i = 1; i < tempRet.length; i++) {
            String[] cur1 = tempRet[i].split(",",2);
            String[] cur2 = tempRet[i].split("}",2);
            String[] cur3 = tempRet[i].split("/",2);
            String[] cur = cur1;
            if(cur2[0].length() < cur[0].length()) {
                cur = cur2;
            }
            if(cur3[0].length() < cur[0].length()) {
                cur = cur3;
            }
            tempRet[i] = cur[0] + "/*";
            if(cur == cur1) {
                tempRet[i] += ",";
            }else if(cur == cur2) {
                tempRet[i] += "}";
            }else {
                tempRet[i] += "/";
            }
            tempRet[i] += cur[1];
            retClause = retClause + tempRet[i];
        }
        output += "return\n";
        output += retClause+"\n";
        writer w = new writer();
        w.writing("xquery_rewritten.txt",output);
        return output;
    }

    private String PrintJoinCond(LinkedList<String> ret0, LinkedList<String> ret1, String output) {
        output += "                 [";
        for(int i = 0; i < ret0.size();i++) {
            output +=ret0.get(i);
            if(i != ret0.size()-1) {
                output +=",";
            }
        }
        output +="], [";
        for(int i = 0; i < ret1.size();i++) {
            output +=ret1.get(i);
            if(i != ret1.size()-1) {
                output +=",";
            }
        }
        output += "]  ";
        return output;
    }

    private String Print2Join(List<HashSet<String>> classify, XQueryGrammarParser.FLWRContext ctx, String output,String[][] cond,int[][] relaWhere) {
        int numFor = ctx.forClause().var().size();
        for(int i = 0; i < 2; i++) {
            HashSet<String> curSet = classify.get(i);
            String tuples = "";
            int count = 0;
            for(int k = 0; k < numFor; k++) {
                String key = ctx.forClause().var(k).getText();
                if(curSet.contains(key)){
                    if(count == 0) {
                        output += "for " + key + " in " + ctx.forClause().xq(k).getText();
                        count++;
                    }else {
                        output += ",\n";
                        output += "                   " + key + " in " + ctx.forClause().xq(k).getText();
                    }
                    if(tuples.equals("")) {
                        tuples = tuples + " <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                    }else {
                        tuples = tuples + ", <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                    }
                }
            }
            output += "\n";
            for(int j = 0;j < cond.length;j++) {
                int count1 = 0;
                if(relaWhere[j][1] == -1 && curSet.contains(cond[j][0])) {
                    if(count1 == 0){
                        count1++;
                        output += "where " + cond[j][0] + " eq " + cond[j][1] +"\n";
                    }else {
                        output += " and  " + cond[j][0] + " eq " + cond[j][1] + "\n";
                    }
                }
            }
            tuples = "<tuple> "+tuples+" </tuple>,";
            output += "                  return" + tuples + "\n";
        }
        LinkedList<String> ret0 = new LinkedList<String>();
        LinkedList<String> ret1 = new LinkedList<String>();
        for(int i = 0; i < cond.length; i++) {
            if (relaWhere[i][0] == 1 && relaWhere[i][1] == 0) {
                ret0.add(cond[i][1].substring(1));
                ret1.add(cond[i][0].substring(1));
            }else if(relaWhere[i][0] == 0 && relaWhere[i][1] == 1) {
                ret0.add(cond[i][0].substring(1));
                ret1.add(cond[i][1].substring(1));
            }
        }
        output = PrintJoinCond(ret0,ret1,output);
        output += ")\n";
        return output;
    }
    private String Print3Join(List<HashSet<String>> classify, XQueryGrammarParser.FLWRContext ctx,String output,String[][] cond,int[][] relaWhere) {
        int numFor = ctx.forClause().var().size();
        HashSet<String> curSet = classify.get(2);
        String tuples = "";
        int count = 0;
        for(int k = 0; k < numFor; k++) {
            String key = ctx.forClause().var(k).getText();
            if(curSet.contains(key)){
                if(count == 0) {
                    output += ",for " + key + " in " + ctx.forClause().xq(k).getText();
                    count++;
                }else {
                    output += ",\n";
                    output += "                   " + key + " in " + ctx.forClause().xq(k).getText();
                }
                if(tuples.equals("")) {
                    tuples = tuples + " <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                }else {
                    tuples = tuples + ", <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                }
            }
        }
        output += "\n";
        for(int j = 0;j < cond.length;j++) {
            int count1 = 0;
            if(relaWhere[j][1] == -1 && curSet.contains(cond[j][0])) {
                if(count1 == 0){
                    count1++;
                    output += "where " + cond[j][0] + " eq " + cond[j][1] +"\n";
                }else {
                    output += " and  " + cond[j][0] + " eq " + cond[j][1] + "\n";
                }
            }
        }
        tuples = "<tuple> "+tuples+" </tuple>,";
        output += "                  return" + tuples + "\n";
        LinkedList<String> ret0 = new LinkedList<String>();
        LinkedList<String> ret2 = new LinkedList<String>();
        for(int i = 0; i < cond.length; i++) {
            if (relaWhere[i][0] == 2 && (relaWhere[i][1] == 1 || relaWhere[i][1] == 0)){
                ret0.add(cond[i][1].substring(1));
                ret2.add(cond[i][0].substring(1));
            }else if((relaWhere[i][0] == 1 || relaWhere[i][0] == 0) && relaWhere[i][1] == 2) {
                ret0.add(cond[i][0].substring(1));
                ret2.add(cond[i][1].substring(1));
            }
        }
        output = PrintJoinCond(ret0,ret2,output);
        output += ")\n";
        return output;
    }
    private String Print4Join(List<HashSet<String>> classify, XQueryGrammarParser.FLWRContext ctx,String output,String[][] cond,int[][] relaWhere) {
        int numFor = ctx.forClause().var().size();
        HashSet<String> curSet = classify.get(3);
        String tuples = "";
        int count = 0;
        for(int k = 0; k < numFor; k++) {
            String key = ctx.forClause().var(k).getText();
            if(curSet.contains(key)){
                if(count == 0) {
                    output += ",for " + key + " in " + ctx.forClause().xq(k).getText();
                    count++;
                }else {
                    output += ",\n";
                    output += "                   " + key + " in " + ctx.forClause().xq(k).getText();
                }
                if(tuples.equals("")) {
                    tuples = tuples + " <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                }else {
                    tuples = tuples + ", <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                }
            }
        }
        output += "\n";
        for(int j = 0;j < cond.length;j++) {
            int count1 = 0;
            if(relaWhere[j][1] == -1 && curSet.contains(cond[j][0])) {
                if(count1 == 0){
                    count1++;
                    output += "where " + cond[j][0] + " eq " + cond[j][1] +"\n";
                }else {
                    output += " and  " + cond[j][0] + " eq " + cond[j][1] + "\n";
                }
            }
        }
        tuples = "<tuple> "+tuples+" </tuple>,";
        output += "                  return" + tuples + "\n";
        LinkedList<String> ret0 = new LinkedList<String>();
        LinkedList<String> ret2 = new LinkedList<String>();
        for(int i = 0; i < cond.length; i++) {
            if (relaWhere[i][0] == 3 && (relaWhere[i][1] >= 0 && relaWhere[i][1] <= 2)){
                ret0.add(cond[i][1].substring(1));
                ret2.add(cond[i][0].substring(1));
            }else if((relaWhere[i][0] >= 0 && relaWhere[i][0] <= 2) && relaWhere[i][1] == 3) {
                ret0.add(cond[i][0].substring(1));
                ret2.add(cond[i][1].substring(1));
            }
        }
        output = PrintJoinCond(ret0,ret2,output);
        output += ")\n";
        return output;
    }
    private String Print5Join(List<HashSet<String>> classify, XQueryGrammarParser.FLWRContext ctx,String output,String[][] cond,int[][] relaWhere) {
        int numFor = ctx.forClause().var().size();
        HashSet<String> curSet = classify.get(4);
        String tuples = "";
        int count = 0;
        for(int k = 0; k < numFor; k++) {
            String key = ctx.forClause().var(k).getText();
            if(curSet.contains(key)){
                if(count == 0) {
                    output += ",for " + key + " in " + ctx.forClause().xq(k).getText();
                    count++;
                }else {
                    output += ",\n";
                    output += "                   " + key + " in " + ctx.forClause().xq(k).getText();
                }
                if(tuples.equals("")) {
                    tuples = tuples + " <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                }else {
                    tuples = tuples + ", <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                }
            }
        }
        output += "\n";
        for(int j = 0;j < cond.length;j++) {
            int count1 = 0;
            if(relaWhere[j][1] == -1 && curSet.contains(cond[j][0])) {
                if(count1 == 0){
                    count1++;
                    output += "where " + cond[j][0] + " eq " + cond[j][1] +"\n";
                }else {
                    output += " and  " + cond[j][0] + " eq " + cond[j][1] + "\n";
                }
            }
        }
        tuples = "<tuple> "+tuples+" </tuple>,";
        output += "                  return" + tuples + "\n";
        LinkedList<String> ret0 = new LinkedList<String>();
        LinkedList<String> ret2 = new LinkedList<String>();
        for(int i = 0; i < cond.length; i++) {
            if (relaWhere[i][0] == 2 && (relaWhere[i][1] == 1 || relaWhere[i][1] == 0)){
                ret0.add(cond[i][1].substring(1));
                ret2.add(cond[i][0].substring(1));
            }else if((relaWhere[i][0] == 1 || relaWhere[i][0] == 0) && relaWhere[i][1] == 2) {
                ret0.add(cond[i][0].substring(1));
                ret2.add(cond[i][1].substring(1));
            }
        }
        output = PrintJoinCond(ret0,ret2,output);
        output += ")\n";
        return output;
    }
    private String Print6Join(List<HashSet<String>> classify, XQueryGrammarParser.FLWRContext ctx,String output,String[][] cond,int[][] relaWhere) {
        int numFor = ctx.forClause().var().size();
        HashSet<String> curSet = classify.get(5);
        String tuples = "";
        int count = 0;
        for(int k = 0; k < numFor; k++) {
            String key = ctx.forClause().var(k).getText();
            if(curSet.contains(key)){
                if(count == 0) {
                    output += ",for " + key + " in " + ctx.forClause().xq(k).getText();
                    count++;
                }else {
                    output += ",\n";
                    output += "                   " + key + " in " + ctx.forClause().xq(k).getText();
                }
                if(tuples.equals("")) {
                    tuples = tuples + " <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                }else {
                    tuples = tuples + ", <" + key.substring(1) + "> " + " {" + key + "} " + " </" + key.substring(1) + ">";
                }
            }
        }
        output += "\n";
        for(int j = 0;j < cond.length;j++) {
            int count1 = 0;
            if(relaWhere[j][1] == -1 && curSet.contains(cond[j][0])) {
                if(count1 == 0){
                    count1++;
                    output += "where " + cond[j][0] + " eq " + cond[j][1] +"\n";
                }else {
                    output += " and  " + cond[j][0] + " eq " + cond[j][1] + "\n";
                }
            }
        }
        tuples = "<tuple> "+tuples+" </tuple>,";
        output += "                  return" + tuples + "\n";
        LinkedList<String> ret0 = new LinkedList<String>();
        LinkedList<String> ret2 = new LinkedList<String>();
        for(int i = 0; i < cond.length; i++) {
            if (relaWhere[i][0] == 2 && (relaWhere[i][1] == 1 || relaWhere[i][1] == 0)){
                ret0.add(cond[i][1].substring(1));
                ret2.add(cond[i][0].substring(1));
            }else if((relaWhere[i][0] == 1 || relaWhere[i][0] == 0) && relaWhere[i][1] == 2) {
                ret0.add(cond[i][0].substring(1));
                ret2.add(cond[i][1].substring(1));
            }
        }
        output = PrintJoinCond(ret0,ret2,output);
        output += ")\n";
        return output;
    }


    @Override public LinkedList<Node> visitJoinXQ(XQueryGrammarParser.JoinXQContext ctx) { return visitChildren(ctx); }
    @Override public LinkedList<Node> visitJoinClause(XQueryGrammarParser.JoinClauseContext ctx) {
        LinkedList<Node> left = visit(ctx.xq(0));
        LinkedList<Node> right = visit(ctx.xq(1));
        int idSize = ctx.idList(0).ID().size();
        String [] idListLeft = new String [idSize];
        String [] idListRight = new String [idSize];
        for (int i = 0; i < idSize; i++){
            idListLeft[i] = ctx.idList(0).ID(i).getText();
            idListRight[i] = ctx.idList(1).ID(i).getText();
        }
        HashMap<String, LinkedList<Node>> hashMapOnLeft = buildHashTable(left, idListLeft);
        LinkedList<Node> result = probeJoin(hashMapOnLeft, right, idListLeft, idListRight);

        return result;
    }
    private LinkedList<Node> probeJoin(HashMap<String, LinkedList<Node>> hashMapOnLeft, LinkedList<Node> right, String [] idListLeft, String []idListRight){
        LinkedList<Node> result = new LinkedList<>();
        for (Node tuple: right){
            LinkedList<Node> children = getChildren(tuple);
            String key = "";
            for (String hashAtt: idListRight) {
                for (Node child: children){
                    if (hashAtt.equals(child.getNodeName())) {
                        key += child.getFirstChild().getTextContent();
                    }
                }
            }

            if (hashMapOnLeft.containsKey(key))
                result.addAll(product(hashMapOnLeft.get(key),tuple));
        }
        return result;
    }
    private LinkedList<Node> product(LinkedList<Node> leftList, Node right){
        LinkedList<Node> result = new LinkedList<>();
        for (Node left: leftList){
            LinkedList<Node> newTupleChildren = getChildren(left);
            newTupleChildren.addAll(getChildren(right));
            result.add(makeElem("tuple", newTupleChildren));
        }
        return result;
    }

    private HashMap buildHashTable(LinkedList<Node> tupleList, String [] hashAtts){
        HashMap<String, LinkedList<Node>> result = new HashMap<>();
        for (Node tuple: tupleList){
            LinkedList<Node> children = getChildren(tuple);
            String key = "";
            for (String hashAtt: hashAtts) {
                for (Node child: children){
                    if (hashAtt.equals(child.getNodeName()))
                        key += child.getFirstChild().getTextContent();
                }
            }
            if (result.containsKey(key))
                result.get(key).add(tuple);
            else{
                LinkedList<Node> value = new LinkedList<>();
                value.add(tuple);
                result.put(key, value);
            }
        }
        return result;
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
        LinkedList<Node> result = new LinkedList<>();
        copyOf(visit(ctx.xq(0)), result);
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
    /*
    private  LinkedList<Node> forClauseHelper(int k, XQueryParser.ForClauseContext ctx){
        String key = ctx.var(k).getText();
        LinkedList<Node> valueList = visit(ctx.xq(k));

        for (Node node: valueList){

            HashMap<String, LinkedList<Node>> contextMapOld = new HashMap<>(contextMap);
            contextStack.push(contextMapOld);

            LinkedList<Node> value = new LinkedList<>(); value.add(node);
            contextMap.put(key, value);
            if (k+1 < ctx.var().size())
                forClauseHelper(k+1, ctx);


            contextMap = contextStack.pop();
        }

    }*/

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

        return visit(ctx.rt());
    }
    @Override public LinkedList<Node> visitTagReturn(XQueryGrammarParser.TagReturnContext ctx) {
        String tagName = ctx.startTag().tagName().getText();
        LinkedList<Node> nodeList = visit(ctx.rt());
        Node node = makeElem(tagName, nodeList);
        LinkedList<Node> result = new  LinkedList<>();
        result.add(node);
        return result;
    }


    @Override public LinkedList<Node> visitXqReturn(XQueryGrammarParser.XqReturnContext ctx) { return visit(ctx.xq()); }

    @Override public LinkedList<Node> visitCommaReturn(XQueryGrammarParser.CommaReturnContext ctx) {
        LinkedList<Node> result = visit(ctx.rt(0));
        result.addAll(visit(ctx.rt(1)));
        return result;
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

    public static LinkedList<Node> getChildren(Node curNode){
        LinkedList<Node> childrenList = new LinkedList<Node>();
        for (int i = 0; i < curNode.getChildNodes().getLength(); i++) {
            childrenList.add(curNode.getChildNodes().item(i));
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