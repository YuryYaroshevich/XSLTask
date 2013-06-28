<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:pr="http://www.epam.com/products">
	<xsl:output method="html" />

	<xsl:param name="categoryName" />
	<xsl:param name="subcategoryName" />
	
	<xsl:template match="/pr:products">
		<html>
			<head>
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
				<form action="controller">
					<input type="hidden" name="command" value="ADD_GOOD" />
					<input type="hidden" name="categoryName" value="{$categoryName}" />
					<input type="hidden" name="subcategoryName" value="{$subcategoryName}" />
					<input type="submit" value="ADD PRODUCT" />
				</form>
			</body>
		</html>
	</xsl:template>

	<xsl:include href="good.xslt" />
</xsl:stylesheet>