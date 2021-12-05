<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <br>
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3>Validar regras</h3>
            </div>
            <div class="panel-body">
                <form:form method="post" modelAttribute="solicitacao" action="/validar-regra">
                    <fieldset class="form-group">
                        <form:label path="nome">Nome beneficiário</form:label>
                        <form:input path="nome" maxlength="255" type="text" class="form-control"
                                    required="required" disabled="${solicitacao.sucesso != null}"/>
                        <form:errors path="nome" cssClass="text-warning"/>
                    </fieldset>
                    <fieldset class="form-group">
                        <form:label path="idade">Idade</form:label>
                        <form:input path="idade" maxlength="3" type="number" class="form-control"
                                    required="required" disabled="${solicitacao.sucesso != null}"/>
                        <form:errors path="idade" cssClass="text-warning"/>
                    </fieldset>
                    <fieldset class="form-group">
                        <form:label path="sexo">Sexo</form:label>
                        <form:select path="sexo" type="text" class="form-control" required="required"
                                     disabled="${solicitacao.sucesso != null}">
                            <form:option value="" label="--------"/>
                            <form:option value="M" label="Masculino"/>
                            <form:option value="F" label="Feminino"/>
                        </form:select>
                        <form:errors path="sexo" cssClass="text-warning"/>
                    </fieldset>
                    <fieldset class="form-group">
                        <form:label path="cdProcedimento">Código Procedimento</form:label>
                        <form:input path="cdProcedimento" maxlength="8" type="number" class="form-control"
                                    required="required" disabled="${solicitacao.sucesso != null}"/>
                        <form:errors path="cdProcedimento" cssClass="text-warning"/>
                    </fieldset>
                    <br>
                    <c:if test="${solicitacao.sucesso == null}">
                        <form:button class="btn btn-success">Fazer solicitação</form:button>
                    </c:if>
                    <c:if test="${solicitacao.sucesso != null}">
                        <a class="btn btn-warning" href="/criar-validacao">Nova solicitação</a>
                        <i class="${solicitacao.sucesso ? 'bi bi-patch-check-fill' : 'bi bi-patch-exclamation-fill'}"
                           style="margin-left: 10px; color: ${solicitacao.sucesso ? 'green' : 'red'}"></i>
                        <c:if test="${solicitacao.sucesso}">
                            <span>${solicitacao.sexo.equals("M") ? "O beneficiário" : "A beneficiária"} ${solicitacao.nome} é elegível para realizar o procedimento informado!</span>
                        </c:if>
                        <c:if test="${!solicitacao.sucesso}">
                            <span>${solicitacao.sexo.equals("M") ? "O beneficiário" : "A beneficiária"} ${solicitacao.nome} não é elegível para realizar o procedimento informado!</span>
                        </c:if>
                    </c:if>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf" %>