package io.windoctor7.github.utils

import org.commonmark.node.Image
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.AttributeProvider
import org.commonmark.renderer.html.AttributeProviderContext
import org.commonmark.renderer.html.AttributeProviderFactory
import org.commonmark.renderer.html.HtmlRenderer

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 02/05/17.
 */
class MarkdownConverter {

    static String toHtml(String input){
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().attributeProviderFactory(new AttributeProviderFactory() {
            AttributeProvider create(AttributeProviderContext context) {
                return new ImageAttributeProvider();
            }
        }).build();
        return renderer.render(parser.parse(input))
    }
}

class ImageAttributeProvider implements AttributeProvider {
    @Override
    void setAttributes(Node node, String tagName, Map<String, String> attributes) {
        if (node instanceof Image) {
            attributes.put("class", "img-responsive center-block");
        }
    }
}
