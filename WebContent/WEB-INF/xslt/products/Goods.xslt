<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:pr="http://www.epam.com/products">

	<xsl:include href="good.xslt" />

	<xsl:output method="html" />

	<xsl:param name="categoryName" />
	<xsl:param name="subcategoryName" />

	<xsl:template match="/pr:products">
		<html>
			<head>
				<link rel="stylesheet" href="css/pushToRight.css" type="text/css"
					media="screen" />
					
				<title>
					Goods of
					<xsl:value-of select="$subcategoryName" />
				</title>
			</head>
			<body>
				<h1>
					Goods of
					<xsl:value-of select="$subcategoryName" />
				</h1>
				<xsl:apply-templates
					select="pr:category[@name=$categoryName]/
					        pr:subcategory[@name=$subcategoryName]/pr:good" />
				<br></br>	        
				<form action="controller">
					<input type="hidden" name="command" value="ADD_GOOD" />
					<input type="hidden" name="categoryName" value="{$categoryName}" />
					<input type="hidden" name="subcategoryName" value="{$subcategoryName}" />
					<input class="push-to-right" type="submit" value="ADD PRODUCT" />
				</form>
				<a class="push-to-right"
					href="controller?command=SUBCATEGORIES&amp;categoryName={$categoryName}">BACK</a>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>