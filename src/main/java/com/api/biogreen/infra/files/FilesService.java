package com.api.biogreen.infra.files;

import com.api.biogreen.infra.exception.UploadFileException;
import com.api.biogreen.utils.TratadorArquivo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FilesService {

    public String salvar(String uploadDir, MultipartFile file){

        Path diretorio = Paths.get(uploadDir);
        Path caminhoExportar = diretorio.resolve(UUID.randomUUID() + TratadorArquivo.obterExtensao(file.getOriginalFilename()));

        try {
            file.transferTo(caminhoExportar);
        } catch (IOException e) {
            throw new UploadFileException("Erro interno ao salvar o arquivo no diretório");
        }

        return caminhoExportar.toString();
    }

    public void atualizar(String url, MultipartFile file){

        Path caminhoArquivo = Paths.get(url);

        try {
            file.transferTo(caminhoArquivo);
        } catch (IOException e) {
            throw new UploadFileException("Erro interno ao atualizar o arquivo");
        }

    }

    public void deletar(String url){

        Path caminhoArquivo = Paths.get(url);

        try {
            Files.deleteIfExists(caminhoArquivo);
        } catch (IOException e) {
            throw new UploadFileException("Erro interno ao deletar o arquivo");
        }
    }



}
