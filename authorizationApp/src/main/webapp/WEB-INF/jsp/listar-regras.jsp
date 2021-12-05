<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <br>
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3>Lista de regras para autorização de procedimentos</h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Código Procedimento</th>
                        <th>Idade do beneficário</th>
                        <th>Sexo do beneficiário</th>
                        <th>Permitido?</th>
                        <th>Usuário criação</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${regras}" var="regra">
                        <tr>
                            <td>${regra.id}</td>
                            <td>${regra.cdProcedimento}</td>
                            <td>${regra.idade}</td>
                            <td>${regra.sexo == 'M' ? 'Masculino' : 'Feminino'}</td>
                            <td><input type="checkbox" disabled ${regra.permitido ? 'checked' : ''}></td>
                            <td>${regra.usuario}</td>
                            <td>
                                <a type="button" class="btn btn-success" href="/editar-regra?id=${regra.id}">Update</a>
                                <a type="button" class="btn btn-danger" href="/excluir-regra?id=${regra.id}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf" %>