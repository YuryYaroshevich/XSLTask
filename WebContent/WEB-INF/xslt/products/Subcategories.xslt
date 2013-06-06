<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />

	<!-- match <products> element -->
	<xsl:param name="categoryName" />
	<xsl:template match="/products">		
		<html>
			<head>
				<title>
					Subcategories of
					<xsl:value-of select="$categoryName" />
				</title>
			</head>
			<body>
				<h1>
					Subcategoriess of
					<xsl:value-of select="$categoryName" />
				</h1>
				<ul>
					<xsl:apply-templates select="category[@name=$categoryName]/subcategory" />
				</ul>
			</body>
		</html>
	</xsl:template>

	<!-- match the <subcategory> element -->
	<xsl:template match="subcategory">
		<li>
			<a href="controller?command=GOODS&amp;categoryName={$categoryName}&amp;subcategoryName={@name}">
				<xsl:value-of select="@name" />
				(<xsl:value-of select="count(good)" />)
			</a>
		</li>
	</xsl:template>
</xsl:stylesheet>