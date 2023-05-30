package com.teacherfinder.security.api;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teacherfinder.security.api.dtos.ApplicantResource;
import com.teacherfinder.security.api.dtos.CreateApplicantResource;
import com.teacherfinder.security.api.mapper.ApplicantMapper;
import com.teacherfinder.security.domain.model.aggregate.Applicant;
import com.teacherfinder.security.domain.model.valueObjects.CurriculumVitae;
import com.teacherfinder.security.domain.service.ApplicantService;

@CrossOrigin(origins = "*", methods ={
    RequestMethod.POST,
    RequestMethod.GET,
    RequestMethod.PUT,
    RequestMethod.DELETE
})

@RestController
@RequestMapping("api/v1/applicant")
public class ApplicantController {
    
    @Autowired
    ApplicantService service;

    @Autowired
    ApplicantMapper mapper;

    @PostMapping
    public ApplicantResource create(CreateApplicantResource createApplicantResource){

        Applicant applicant = mapper.toModel(createApplicantResource);

        Applicant response = service.create(applicant);

        return mapper.toResource(response);    
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long applicantId, @RequestParam("file") MultipartFile file) {
        
        try {
            // Verificar si el archivo es un PDF
            if (!file.getContentType().equals("application/pdf")) {
                return ResponseEntity.badRequest().body("Por favor, selecciona un archivo PDF.");
            }

            // Leer el contenido del archivo
            byte[] contenido = file.getBytes();

            // Crear una instancia de la entidad PdfFile
            CurriculumVitae pdfFile = new CurriculumVitae(contenido);

            // Guardar el archivo PDF en la base de datos
            service.addCV(applicantId,pdfFile);

            return ResponseEntity.ok("¡Archivo PDF subido correctamente!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir el archivo PDF.");
        }
    }

    @GetMapping("/cv/{id}")
    public ResponseEntity<InputStreamResource> downloadPdfFile(@PathVariable Long id) {
        // Buscar el archivo PDF en la base de datos por ID

        Applicant applicant = service.getCv(id);

        CurriculumVitae pdfFile = applicant.getCv();

        // Crear un InputStream a partir del contenido del archivo PDF
        ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfFile.getData());

        // Configurar las cabeceras de la respuesta HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", ""+applicant.getApplicantId());

        // Crear un InputStreamResource a partir del InputStream
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        // Devolver la respuesta con el archivo PDF como contenido
        return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
    }
}
