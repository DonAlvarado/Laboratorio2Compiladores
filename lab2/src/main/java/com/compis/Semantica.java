package com.compis;

public class Semantica extends Lab2BaseVisitor<Object> {

    private SymbolTabla table = new SymbolTabla();

    public SymbolTabla getTable() {
        return table;
    }

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

    @Override
    public Object visitValue(Lab2Parser.ValueContext ctx) {

        if (ctx.BOOL() != null)
            return Boolean.parseBoolean(ctx.BOOL().getText());

        if (ctx.INT() != null)
            return Integer.parseInt(ctx.INT().getText());

        return ctx.STRING().getText().replace("\"", "");
    }

    @Override
    public Object visitAndExpr(Lab2Parser.AndExprContext ctx) {

        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));

        /* NO STRINGS */
        if (left instanceof String || right instanceof String) {
            throw new RuntimeException("Error semántico: Las variables de tipo string no están permitidas.");
        }

        /* VALIDAR TIPOS IGUALES */
        if (!left.getClass().equals(right.getClass())) {
            throw new RuntimeException("Error semántico: No se pueden combinar variables de distintos tipos.");
        }

        /* convertir int a boolean */
        if (left instanceof Integer) {
            left = ((int) left) != 0;
            right = ((int) right) != 0;
        }

        return (Boolean) left && (Boolean) right;
    }

    @Override
    public Object visitNotExpr(Lab2Parser.NotExprContext ctx) {

        Object value = visit(ctx.expr());

        if (value instanceof String) {
            throw new RuntimeException("Error semántico: string no permitido.");
        }

        if (value instanceof Integer)
            value = ((int) value) != 0;

        return !(Boolean) value;
    }

    @Override
    public Object visitIdExpr(Lab2Parser.IdExprContext ctx) {

        String id = ctx.ID().getText();

        if (!table.exists(id)) {
            throw new RuntimeException("Variable no definida: " + id);
        }

        Object value = table.get(id).value;

        if (value instanceof String) {
            throw new RuntimeException("Error semántico: string no permitido.");
        }

        return value;
    }

    @Override
    public Object visitProgram(Lab2Parser.ProgramContext ctx) {

        for (Lab2Parser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }

        return visit(ctx.expr());
    }
}