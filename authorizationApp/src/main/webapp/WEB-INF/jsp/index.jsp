<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">Bem vindo!</div>
        <div class="panel-body">
            Para utilizar o sistema e fazer consultas, cadastros ou validações será necessário utilizar um usuário
            cadastrado.
            Neste sistema há dois tipo de perfis possíveis, tipo adminstrador e tipo usuário.
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Tipo</th>
                    <th>Login</th>
                    <th>Senha</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Administrador</td>
                    <td>admin</td>
                    <td>admin</td>
                </tr>
                <tr>
                    <td>Usuário</td>
                    <td>fulano</td>
                    <td>fulano</td>
                </tr>
                <tr>
                    <td>Usuário</td>
                    <td>cicrano</td>
                    <td>cicrano</td>
                </tr>
                <tr>
                    <td>Usuário</td>
                    <td>beltrano</td>
                    <td>beltrano</td>
                </tr>
                </tbody>
            </table>
            <br>
            <span class="alert alert-warning">
                <i class="bi bi-exclamation-triangle-fill"></i>
                O administrador consegue visualizar todos os registros enquanto os usuários conseguem visualizar somente os registros criados por eles.
            </span>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf" %>