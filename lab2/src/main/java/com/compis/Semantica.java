package com.compis;

public class Semantica extends Lab2BaseVisitor<Object> {

    private SymbolTabla table = new SymbolTabla();

    public SymbolTabla getTable() {
        return table;
    }

    /* declaraciones */
    @Override
    public Object visitStatement(Lab2Parser.StatementContext ctx) {

        String id = ctx.ID().getText();
        Object value = visit(ctx.value());

        String type;

        if (value instanceof Boolean) type = "boolean";
        else if (value instanceof Integer) type = "int";
        else type = "string";

        table.add(new Symbol(id, type, value));

        return null;
    }

    /* valores */
    @Override
    public Object visitValue(Lab2Parser.ValueContext ctx) {

        if (ctx.BOOL() != null)
            return Boolean.parseBoolean(ctx.BOOL().getText());

        if (ctx.INT() != null)
            return Integer.parseInt(ctx.INT().getText());

        return ctx.STRING().getText().replace("\"", "");
    }

    /* expresiones */
    @Override
    public Object visitExpr(Lab2Parser.ExprContext ctx) {

        /* AND */
        if (ctx.getChildCount() == 3 && ctx.getChild(1).getText().equals("and")) {

            Object left = visit(ctx.getChild(0));
            Object right = visit(ctx.getChild(2));

            /* string prohibido */
            if (left instanceof String || right instanceof String) {
                throw new RuntimeException("Error semántico: Las variables de tipo string no están permitidas en expresiones booleanas.");
            }

            /* convertir int a boolean */
            if (left instanceof Integer)
                left = ((int) left) != 0;

            if (right instanceof Integer)
                right = ((int) right) != 0;

            /* validación final */
            if (!(left instanceof Boolean) || !(right instanceof Boolean)) {
                throw new RuntimeException("Error semántico: tipos incompatibles");
            }

            return (Boolean) left && (Boolean) right;
        }

        /* NOT */
        if (ctx.getChild(0).getText().equals("not")) {

            Object value = visit(ctx.getChild(2));

            if (value instanceof String) {
                throw new RuntimeException("Error semántico: Las variables de tipo string no están permitidas en expresiones booleanas.");
            }

            if (value instanceof Integer)
                value = ((int) value) != 0;

            if (!(value instanceof Boolean)) {
                throw new RuntimeException("Error semántico: NOT inválido");
            }

            return !(Boolean) value;
        }

        /* ID */
        if (ctx.ID() != null) {

            String id = ctx.ID().getText();

            if (!table.exists(id)) {
                throw new RuntimeException("Variable no definida: " + id);
            }

            Object value = table.get(id).value;

            if (value instanceof String) {
                throw new RuntimeException("Error semántico: Las variables de tipo string no están permitidas en expresiones booleanas.");
            }

            return value;
        }

        return visitChildren(ctx);
    }

    /* flujo principal */
    @Override
    public Object visitProgram(Lab2Parser.ProgramContext ctx) {

        for (Lab2Parser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }

        return visit(ctx.expr());
    }
}