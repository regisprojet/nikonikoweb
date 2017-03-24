<#import "/spring.ftl" as spring/>
<html>
  <head>
    <title tiles:fragment="title">Messages : Create</title>
    <#include "../includable/bootstrap.ftl"/>
  </head>
  <body onload="document.f.username.focus();">
    <h1>Spring Security Login (Freemarker)</h1>

    <form action="login" method="POST">
      <table>
        <tr><td>User:</td><td><input type='text' name='username' value=''/></td></tr>
        <tr><td>Password:</td><td><input type='password' name='password' value=''/></td></tr>
        <input type="hidden"
            name="${_csrf.parameterName}"
            value="${_csrf.token}"/>
        <tr><td colspan='2'><input name="submit" type="submit"></td></tr>
        <tr><td colspan='2'><input name="reset" type="reset"></td></tr>
      </table>

    </form>

  </body>
</html>

<#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
    <h1>Bad credential</h1>
</#if>