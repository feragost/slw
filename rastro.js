(function() {
	
	document.getElementByXpath("//*[@id='1-1-identificação-do-cidadão']").style.color = "#4C9900";

}).call(this);

function getElementByXpath(path) {
  return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
}
