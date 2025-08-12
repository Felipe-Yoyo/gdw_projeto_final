package com.gdw.projeto_final.gdw_final.service;

import com.gdw.projeto_final.gdw_final.model.Cliente;
import com.gdw.projeto_final.gdw_final.model.Produto;
import com.gdw.projeto_final.gdw_final.repository.ClienteRepository;
import com.gdw.projeto_final.gdw_final.repository.ProdutoRepository;
import com.gdw.projeto_final.gdw_final.util.DomParserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class XmlImportService {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public XmlImportService(ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public void processarXml(MultipartFile file) throws Exception {
        DomParserUtil parser = new DomParserUtil(file.getInputStream());
        List<Produto> produtos = parser.getProdutos();
        List<Cliente> clientes = parser.getClientes();

        produtoRepository.saveAll(produtos);
        clienteRepository.saveAll(clientes);
    }
}
