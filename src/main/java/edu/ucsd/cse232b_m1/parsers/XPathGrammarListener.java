// Generated from XPathGrammar.g4 by ANTLR 4.7.2

package edu.ucsd.cse232b_m1.parsers;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XPathGrammarParser}.
 */
public interface XPathGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code doubleAP}
	 * labeled alternative in {@link XPathGrammarParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterDoubleAP(XPathGrammarParser.DoubleAPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleAP}
	 * labeled alternative in {@link XPathGrammarParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitDoubleAP(XPathGrammarParser.DoubleAPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleAP}
	 * labeled alternative in {@link XPathGrammarParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterSingleAP(XPathGrammarParser.SingleAPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleAP}
	 * labeled alternative in {@link XPathGrammarParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitSingleAP(XPathGrammarParser.SingleAPContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathGrammarParser#doc}.
	 * @param ctx the parse tree
	 */
	void enterDoc(XPathGrammarParser.DocContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathGrammarParser#doc}.
	 * @param ctx the parse tree
	 */
	void exitDoc(XPathGrammarParser.DocContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braceRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterBraceRP(XPathGrammarParser.BraceRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braceRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitBraceRP(XPathGrammarParser.BraceRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleSlashRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterDoubleSlashRP(XPathGrammarParser.DoubleSlashRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleSlashRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitDoubleSlashRP(XPathGrammarParser.DoubleSlashRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTextRP(XPathGrammarParser.TextRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTextRP(XPathGrammarParser.TextRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAttRP(XPathGrammarParser.AttRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAttRP(XPathGrammarParser.AttRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parentRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterParentRP(XPathGrammarParser.ParentRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parentRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitParentRP(XPathGrammarParser.ParentRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selfRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterSelfRP(XPathGrammarParser.SelfRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selfRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitSelfRP(XPathGrammarParser.SelfRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filterRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterFilterRP(XPathGrammarParser.FilterRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filterRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitFilterRP(XPathGrammarParser.FilterRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code commaRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterCommaRP(XPathGrammarParser.CommaRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code commaRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitCommaRP(XPathGrammarParser.CommaRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code childrenRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterChildrenRP(XPathGrammarParser.ChildrenRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code childrenRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitChildrenRP(XPathGrammarParser.ChildrenRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tagRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTagRP(XPathGrammarParser.TagRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tagRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTagRP(XPathGrammarParser.TagRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleSlashRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterSingleSlashRP(XPathGrammarParser.SingleSlashRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleSlashRP}
	 * labeled alternative in {@link XPathGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitSingleSlashRP(XPathGrammarParser.SingleSlashRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterEqFilter(XPathGrammarParser.EqFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitEqFilter(XPathGrammarParser.EqFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterNotFilter(XPathGrammarParser.NotFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitNotFilter(XPathGrammarParser.NotFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterAndFilter(XPathGrammarParser.AndFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitAndFilter(XPathGrammarParser.AndFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterIsFilter(XPathGrammarParser.IsFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitIsFilter(XPathGrammarParser.IsFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterRpFilter(XPathGrammarParser.RpFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitRpFilter(XPathGrammarParser.RpFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braceFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterBraceFilter(XPathGrammarParser.BraceFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braceFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitBraceFilter(XPathGrammarParser.BraceFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterOrFilter(XPathGrammarParser.OrFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orFilter}
	 * labeled alternative in {@link XPathGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitOrFilter(XPathGrammarParser.OrFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathGrammarParser#tagName}.
	 * @param ctx the parse tree
	 */
	void enterTagName(XPathGrammarParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathGrammarParser#tagName}.
	 * @param ctx the parse tree
	 */
	void exitTagName(XPathGrammarParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathGrammarParser#attName}.
	 * @param ctx the parse tree
	 */
	void enterAttName(XPathGrammarParser.AttNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathGrammarParser#attName}.
	 * @param ctx the parse tree
	 */
	void exitAttName(XPathGrammarParser.AttNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathGrammarParser#filename}.
	 * @param ctx the parse tree
	 */
	void enterFilename(XPathGrammarParser.FilenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathGrammarParser#filename}.
	 * @param ctx the parse tree
	 */
	void exitFilename(XPathGrammarParser.FilenameContext ctx);
}