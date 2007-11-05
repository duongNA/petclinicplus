<#import "spring.ftl" as spring />

<html>
  <head>
    <title>Login</title>
  </head>

  <body>
    <h2>Login</h2>


    <#if RequestParameters.login_error?exists>
      <font color="red">
        Your login attempt was not successful, try again.<BR>
        Reason: ${acegiException.message}
      </font>
    </#if>


    <form action="<@spring.url '/j_acegi_security_check'/>" method="POST">
      <table>
        <tr><td>User:</td><td><input type='text' name='j_username' <#if RequestParameters.login_error?exists>value='${acegiLastUser}'</#if> ></td></tr>
        <tr><td>Password:</td><td><input type='password' name='j_password'></td></tr>
        <tr><td><input type="checkbox" name="_acegi_security_remember_me"></td><td>Remember me for two weeks</td></tr>
        <tr><td colspan='2'><input name="submit" type="submit"><input name="reset" type="reset"></td></tr>
      </table>

    </form>
	<br/>
    <small>
	<P>Valid users:

	<br/>username <b>admin</b>, password <b>admin</b> (supervisor)
	<br/>username <b>user</b>, password <b>user</b> (normal user)
	<br/>username <b>guest</b>, password <b>guest</b> (normal user, disabled)

	</small
  </body>
</html>
