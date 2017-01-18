package ims.utils.dom;


import org.dom4j.Element;
import org.dom4j.Node;

public class DomUtils 
{
	public static String getSubNodeText(Node node, Expression exp)
	{
		return getSubNodeText(node, exp, null);
	}
	
	public static String getSubNodeText(Node node, Expression exp, String def)
	{
		if (node == null) return def;
		Node n2 = node.selectSingleNode( exp.getExpression() );
		if (n2 == null) return def;
		else if (n2.getText() == null) return def;
		else return n2.getText();
	}

	public static String getSubNodeAttVal(Node node, Expression exp, Attribute attribute)
	{
		return getSubNodeAttVal(node, exp, attribute, null);
	}

	public static String getSubNodeAttVal(Node node, Expression exp, Attribute attribute, String def)
	{
		if (node == null) return def;
		Element valueEl = (Element)(node.selectSingleNode(exp.getExpression()));
		if (valueEl == null) return def;
		else if (valueEl.attributeValue(attribute.getName()) == null) return def;
		else return(valueEl.attributeValue(attribute.getName()));
	}
}
