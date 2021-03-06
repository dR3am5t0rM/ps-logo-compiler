package logoCompiler.parser.exprs;

import logoCompiler.lexer.*;
import logoCompiler.lexer.tokens.operators.OperatorToken;
import logoCompiler.parser.Parser;

/*
 * expr:
 *   primary-expr 
 *   binary-expr 
 */
public abstract class Expr {

	public static Expr parse() {

		return fraserHanson(1);
	}

	//Binary Expressions precedence handler from Fraser and Hanson C Compiler book
	private static Expr fraserHanson(int k) {
		int   i;
		Expr  left;
		OperatorToken oper;
		Expr  right;
		left = PrimaryExpr.parse();

		Parser.t = Lexer.lex();

		for (i = Parser.t.precedence(); i >= k; i--) {
			while (Parser.t.precedence() == i) {
				oper = (OperatorToken) Parser.t; 
				Parser.t = Lexer.lex();
				right = fraserHanson(i+1);
				left  = new BinaryExpr(left, oper, right); 
			}
		}
		return left;
	}

	public abstract void codegen();
}
