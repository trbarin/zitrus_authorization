<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <br>
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3>Criar nova regra de autorização de procedimento</h3>
            </div>
            <div class="panel-body">
                <form:form method="post" modelAttribute="regra" action="/salvar-regra">
                    <form:hidden path="id"/>
                    <form:hidden path="usuario"/>
                    <fieldset class="form-group">
                        <form:label path="cdProcedimento">Código Procedimento</form:label>
                        <form:input path="cdProcedimento" maxlength="8" type="number" class="form-control"
                                    required="required"/>
                        <form:errors path="cdProcedimento" cssClass="text-warning"/>
                    </fieldset>
                    <fieldset class="form-group">
                        <form:label path="idade">Idade</form:label>
                        <form:input path="idade" maxlength="3" type="number" class="form-control"
                                    required="required"/>
                        <form:errors path="idade" cssClass="text-warning"/>
                    </fieldset>
                    <fieldset class="form-group">
                        <form:label path="sexo">Sexo</form:label>
                        <form:select path="sexo" type="text" class="form-control" required="required">
                            <form:option value="" label="--------"/>
                            <form:option value="M" label="Masculino"/>
                            <form:option value="F" label="Feminino"/>
                        </form:select>
                        <form:errors path="sexo" cssClass="text-warning"/>
                    </fieldset>
                    <fieldset>
                        <form:label path="permitido">Permitido</form:label>
                        <form:checkbox path="permitido" class="form-check"/>
                        <form:errors path="permitido" cssClass="text-warning"/>
                    </fieldset>
                    <br>
                    <button type="submit" class="btn btn-success">Salvar</button>
                    <a class="btn btn-danger" href="/listar-regras">Cancelar</a>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf" %>