<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />

	<xsl:template match="/products">
		<xsl:param name="subcategoryName" />
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
					select="category/subcategory[@name=$subcategoryName]/good" />
				<form action="controller">
					<input type="hidden" name="command" value="ADD_PRODUCT" />
					<input type="submit" value="ADD PRODUCT" />
				</form>
			</body>
		</html>
	</xsl:template>

	<xsl:include href="Good.xslt" />
</xsl:stylesheet>