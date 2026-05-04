// Generated from src/main/antlr4/Lab2.g4 by ANTLR 4.13.2

package com.compis;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Lab2Parser}.
 */
public interface Lab2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Lab2Parser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(Lab2Parser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link Lab2Parser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(Lab2Parser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link Lab2Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(Lab2Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Lab2Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(Lab2Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link Lab2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(Lab2Parser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link Lab2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(Lab2Parser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link Lab2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(Lab2Parser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link Lab2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(Lab2Parser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link Lab2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(Lab2Parser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link Lab2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(Lab2Parser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Lab2Parser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(Lab2Parser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link Lab2Parser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(Lab2Parser.ValueContext ctx);
}