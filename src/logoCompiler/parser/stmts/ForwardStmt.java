package logoCompiler.parser.stmts;

import logoCompiler.Writer;
import logoCompiler.lexer.Lexer;
import logoCompiler.parser.Parser;
import logoCompiler.parser.exprs.Expr;

public class ForwardStmt extends Stmt {
	Expr expr;

	public ForwardStmt(Expr expr) {
		this.expr = expr;
	}

	public static Stmt parse() {

		Parser.t = Lexer.lex();
		Expr expr = Expr.parse();
		return new ForwardStmt(expr);
	}

	@Override
	public void codegen() {
		expr.codegen();
		Writer.write("Forward");
	}
	
	public Expr getExpr(){
		return expr;
	}
}
