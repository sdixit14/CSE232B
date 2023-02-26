// Generated from XQueryGrammar.g4 by ANTLR 4.7.2

package edu.ucsd.cse232b_m1.parsers;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XQueryGrammarParser}.
 */
public interface XQueryGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code FLWR}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterFLWR(XQueryGrammarParser.FLWRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FLWR}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitFLWR(XQueryGrammarParser.FLWRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleSlashXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterSingleSlashXQ(XQueryGrammarParser.SingleSlashXQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleSlashXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitSingleSlashXQ(XQueryGrammarParser.SingleSlashXQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tagXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterTagXQ(XQueryGrammarParser.TagXQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tagXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitTagXQ(XQueryGrammarParser.TagXQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code apXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterApXQ(XQueryGrammarParser.ApXQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code apXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitApXQ(XQueryGrammarParser.ApXQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code letXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterLetXQ(XQueryGrammarParser.LetXQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code letXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitLetXQ(XQueryGrammarParser.LetXQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code commaXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterCommaXQ(XQueryGrammarParser.CommaXQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code commaXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitCommaXQ(XQueryGrammarParser.CommaXQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterVarXQ(XQueryGrammarParser.VarXQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitVarXQ(XQueryGrammarParser.VarXQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code scXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterScXQ(XQueryGrammarParser.ScXQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code scXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitScXQ(XQueryGrammarParser.ScXQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braceXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterBraceXQ(XQueryGrammarParser.BraceXQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braceXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitBraceXQ(XQueryGrammarParser.BraceXQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleSlashXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterDoubleSlashXQ(XQueryGrammarParser.DoubleSlashXQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleSlashXQ}
	 * labeled alternative in {@link XQueryGrammarParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitDoubleSlashXQ(XQueryGrammarParser.DoubleSlashXQContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryGrammarParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClause(XQueryGrammarParser.ForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryGrammarParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClause(XQueryGrammarParser.ForClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryGrammarParser#letClause}.
	 * @param ctx the parse tree
	 */
	void enterLetClause(XQueryGrammarParser.LetClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryGrammarParser#letClause}.
	 * @param ctx the parse tree
	 */
	void exitLetClause(XQueryGrammarParser.LetClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryGrammarParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(XQueryGrammarParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryGrammarParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(XQueryGrammarParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryGrammarParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterReturnClause(XQueryGrammarParser.ReturnClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryGrammarParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitReturnClause(XQueryGrammarParser.ReturnClauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braceCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterBraceCond(XQueryGrammarParser.BraceCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braceCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitBraceCond(XQueryGrammarParser.BraceCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterOrCond(XQueryGrammarParser.OrCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitOrCond(XQueryGrammarParser.OrCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code satisfyCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterSatisfyCond(XQueryGrammarParser.SatisfyCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code satisfyCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitSatisfyCond(XQueryGrammarParser.SatisfyCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterEmptyCond(XQueryGrammarParser.EmptyCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitEmptyCond(XQueryGrammarParser.EmptyCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterAndCond(XQueryGrammarParser.AndCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitAndCond(XQueryGrammarParser.AndCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterIsCond(XQueryGrammarParser.IsCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitIsCond(XQueryGrammarParser.IsCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterEqCond(XQueryGrammarParser.EqCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitEqCond(XQueryGrammarParser.EqCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterNotCond(XQueryGrammarParser.NotCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notCond}
	 * labeled alternative in {@link XQueryGrammarParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitNotCond(XQueryGrammarParser.NotCondContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryGrammarParser#startTag}.
	 * @param ctx the parse tree
	 */
	void enterStartTag(XQueryGrammarParser.StartTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryGrammarParser#startTag}.
	 * @param ctx the parse tree
	 */
	void exitStartTag(XQueryGrammarParser.StartTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryGrammarParser#endTag}.
	 * @param ctx the parse tree
	 */
	void enterEndTag(XQueryGrammarParser.EndTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryGrammarParser#endTag}.
	 * @param ctx the parse tree
	 */
	void exitEndTag(XQueryGrammarParser.EndTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryGrammarParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(XQueryGrammarParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryGrammarParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(XQueryGrammarParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleAP}
	 * labeled alternative in {@link XQueryGrammarParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterDoubleAP(XQueryGrammarParser.DoubleAPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleAP}
	 * labeled alternative in {@link XQueryGrammarParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitDoubleAP(XQueryGrammarParser.DoubleAPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleAP}
	 * labeled alternative in {@link XQueryGrammarParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterSingleAP(XQueryGrammarParser.SingleAPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleAP}
	 * labeled alternative in {@link XQueryGrammarParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitSingleAP(XQueryGrammarParser.SingleAPContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryGrammarParser#doc}.
	 * @param ctx the parse tree
	 */
	void enterDoc(XQueryGrammarParser.DocContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryGrammarParser#doc}.
	 * @param ctx the parse tree
	 */
	void exitDoc(XQueryGrammarParser.DocContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braceRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterBraceRP(XQueryGrammarParser.BraceRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braceRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitBraceRP(XQueryGrammarParser.BraceRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleSlashRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterDoubleSlashRP(XQueryGrammarParser.DoubleSlashRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleSlashRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitDoubleSlashRP(XQueryGrammarParser.DoubleSlashRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTextRP(XQueryGrammarParser.TextRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTextRP(XQueryGrammarParser.TextRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAttRP(XQueryGrammarParser.AttRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAttRP(XQueryGrammarParser.AttRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parentRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterParentRP(XQueryGrammarParser.ParentRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parentRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitParentRP(XQueryGrammarParser.ParentRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selfRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterSelfRP(XQueryGrammarParser.SelfRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selfRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitSelfRP(XQueryGrammarParser.SelfRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filterRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterFilterRP(XQueryGrammarParser.FilterRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filterRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitFilterRP(XQueryGrammarParser.FilterRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code commaRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterCommaRP(XQueryGrammarParser.CommaRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code commaRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitCommaRP(XQueryGrammarParser.CommaRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code childrenRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterChildrenRP(XQueryGrammarParser.ChildrenRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code childrenRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitChildrenRP(XQueryGrammarParser.ChildrenRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tagRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTagRP(XQueryGrammarParser.TagRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tagRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTagRP(XQueryGrammarParser.TagRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleSlashRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterSingleSlashRP(XQueryGrammarParser.SingleSlashRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleSlashRP}
	 * labeled alternative in {@link XQueryGrammarParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitSingleSlashRP(XQueryGrammarParser.SingleSlashRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterEqFilter(XQueryGrammarParser.EqFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitEqFilter(XQueryGrammarParser.EqFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterNotFilter(XQueryGrammarParser.NotFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitNotFilter(XQueryGrammarParser.NotFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterAndFilter(XQueryGrammarParser.AndFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitAndFilter(XQueryGrammarParser.AndFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterIsFilter(XQueryGrammarParser.IsFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitIsFilter(XQueryGrammarParser.IsFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterRpFilter(XQueryGrammarParser.RpFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitRpFilter(XQueryGrammarParser.RpFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braceFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterBraceFilter(XQueryGrammarParser.BraceFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braceFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitBraceFilter(XQueryGrammarParser.BraceFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void enterOrFilter(XQueryGrammarParser.OrFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orFilter}
	 * labeled alternative in {@link XQueryGrammarParser#f}.
	 * @param ctx the parse tree
	 */
	void exitOrFilter(XQueryGrammarParser.OrFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryGrammarParser#tagName}.
	 * @param ctx the parse tree
	 */
	void enterTagName(XQueryGrammarParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryGrammarParser#tagName}.
	 * @param ctx the parse tree
	 */
	void exitTagName(XQueryGrammarParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryGrammarParser#attName}.
	 * @param ctx the parse tree
	 */
	void enterAttName(XQueryGrammarParser.AttNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryGrammarParser#attName}.
	 * @param ctx the parse tree
	 */
	void exitAttName(XQueryGrammarParser.AttNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryGrammarParser#filename}.
	 * @param ctx the parse tree
	 */
	void enterFilename(XQueryGrammarParser.FilenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryGrammarParser#filename}.
	 * @param ctx the parse tree
	 */
	void exitFilename(XQueryGrammarParser.FilenameContext ctx);
}