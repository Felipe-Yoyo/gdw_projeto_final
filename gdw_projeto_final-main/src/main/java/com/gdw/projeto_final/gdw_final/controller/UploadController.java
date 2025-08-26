package com.gdw.projeto_final.gdw_final.controller;

import com.gdw.projeto_final.gdw_final.service.XmlImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

    private final XmlImportService xmlImportService;

    public UploadController(XmlImportService xmlImportService) {
        this.xmlImportService = xmlImportService;
    }

    @GetMapping("/upload")
    public String uploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleUpload(@RequestParam("xmlFile") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            xmlImportService.processarXml(file);
            redirectAttributes.addFlashAttribute("mensagem", "Upload e importação realizados com sucesso!");
            return "redirect:/consultar-dados";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao processar XML: " + e.getMessage());
            return "redirect:/upload";
        }
    }
}
