<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" />

	<!-- match the document root -->
	<xsl:template match="/">
		<html>
			<head>
				<title></title>
			</head>
			<body>
				<h1>Categories</h1>
				<ul>
					<xsl:apply-templates select="products/category" />
				</ul>
			</body>
		</html>
	</xsl:template>

	<!-- match the <category> element -->
	<xsl:template match="category">
		<li>
			<a href="viewCategory?name={@name}">
				<xsl:value-of select="@name" />
			</a>
		</li>
	</xsl:template>
</xsl:stylesheet>