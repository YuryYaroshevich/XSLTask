<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" />

	<!-- match the <products> element -->
	<xsl:template match="/products">
		<html>
			<head>
				<title>Categories</title>
			</head>
			<body>
				<h1>Categories</h1>
				<ul>
					<xsl:apply-templates select="category" />
				</ul>
			</body>
		</html>
	</xsl:template>

	<!-- match the <category> element -->
	<xsl:template match="category">
		<li>
			<a href="controller?command=SUBCATEGORIES&amp;categoryName={@name}">
				<xsl:value-of select="@name" />
				(<xsl:value-of select="count(subcategory)" />)
			</a>
		</li>
	</xsl:template>
</xsl:stylesheet>