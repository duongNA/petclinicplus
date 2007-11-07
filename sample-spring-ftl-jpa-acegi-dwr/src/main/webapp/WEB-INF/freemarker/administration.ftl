<#import "spring.ftl" as spring />
<#assign authz=JspTaglibs["/WEB-INF/tlds/authz.tld"]>

<html>
	<head>
	</head>

	<body>
	
		<H2>Administration</H2>
		<P>
		<h3>Authorization Data</h3>
		<#if auth?exists>
			<p>Name: ${auth.name}</p>
			<p>Roles:</p>
			<ul>
				<#list auth.authorities as authority>
				<li>${authority}</li>
				</#list>
			</ul>
		<#else>No authentication object available.</#if>
	</body>
	
</html>