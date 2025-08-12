package com.gdw.projeto_final.gdw_final.util;

import com.gdw.projeto_final.gdw_final.model.Cliente;
import com.gdw.projeto_final.gdw_final.model.Produto;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DomParserUtil {

    private final Document document;

    public DomParserUtil(InputStream xmlInput) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        this.document = builder.parse(xmlInput);
        document.getDocumentElement().normalize();
    }

    public List<Produto> getProdutos() {
        List<Produto> list = new ArrayList<>();
        NodeList nodes = document.getElementsByTagName("Produto");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            Produto p = new Produto();
            p.setId(Long.valueOf(getTagValue(e, "id")));
            p.setNome(getTagValue(e, "nome"));
            p.setDescricao(getTagValue(e, "descricao"));
            p.setPreco(new BigDecimal(getTagValue(e, "preco")));
            p.setCategoria(getTagValue(e, "categoria"));
            p.setEstoque(Integer.valueOf(getTagValue(e, "estoque")));
            list.add(p);
        }
        return list;
    }

    public List<Cliente> getClientes() {
        List<Cliente> list = new ArrayList<>();
        NodeList nodes = document.getElementsByTagName("Cliente");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            Cliente c = new Cliente();
            c.setId(Long.valueOf(getTagValue(e, "id")));
            c.setNome(getTagValue(e, "nome"));
            c.setEmail(getTagValue(e, "email"));
            c.setEndereco(getTagValue(e, "endereco"));
            c.setTelefone(getTagValue(e, "telefone"));
            list.add(c);
        }
        return list;
    }

    private String getTagValue(Element parent, String tagName) {
        NodeList nl = parent.getElementsByTagName(tagName);
        if (nl.getLength() > 0) {
            return nl.item(0).getTextContent().trim();
        }
        return null;
    }
}
