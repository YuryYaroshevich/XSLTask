<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />

	<!-- the document root -->
	<xsl:template match="/">
		<html>
			<head>
				<title>Subcategories</title>
			</head>
			<body>
				<h1>Subcategoriess</h1>
				<ul>
					<xsl:apply-templates select="category/subcategory" />
				</ul>
			</body>
		</html>
	</xsl:template>

	<!-- match the <subcategory> element -->
	<xsl:template match="subcategory">
	    
		<li>
			<a href="controller?command=PRODUCTS;subcategory={@name}">
				<xsl:value-of select="@name" />
			</a>
		</li>
	</xsl:template>
</xsl:stylesheet>