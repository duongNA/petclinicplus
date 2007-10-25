<#import "spring.ftl" as spring />

<html>
	<head>
		<title>PetClinic :: a Spring Framework demonstration</title>
		<style type="text/css">
		<!--
			td { padding:3px; }
			div#top {position:absolute; top: 0px; left: 0px; background-color: #E4EFF3;background-image: url(logo.gif);background-position: 100px 1px;background-repeat: no-repeat; height: 50px; width:100%; padding:0px; border: none;margin: 0;}
		// -->
		</style>	
	</head>

	<body>
		<div id="top">&nbsp;</div><br clear="all"><p>&nbsp;</p>
		

<H2><@spring.message "welcome"/></H2>
<P>
<A href="<c:url value="/findOwners.htm"/>">Find owner</A>
<P>
<A href="<c:url value="/vets.htm"/>">Display all veterinarians</A>
<P>
<A href="<c:url value="/html/petclinic.html"/>">Tutorial</A>
<P>
<A href="<c:url value="/docs/index.html"/>">Documentation</A>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
