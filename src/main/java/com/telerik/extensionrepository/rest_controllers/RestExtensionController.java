package com.telerik.extensionrepository.rest_controllers;


import com.telerik.extensionrepository.dto.ExtensionDTO;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.UploadFile;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.service.base.ExtensionService;
import com.telerik.extensionrepository.service.base.FileService;
import com.telerik.extensionrepository.service.base.RestExtensionService;
import com.telerik.extensionrepository.utils.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class RestExtensionController {

    private ExtensionInfoService extensionInfoService;
    private RestExtensionService restExtensionService;
    private FileService fileService;
    private ExtensionService extensionService;

    @Autowired
    public RestExtensionController(ExtensionInfoService extensionInfoService, RestExtensionService restExtensionService, FileService fileService, ExtensionService extensionService){
        this.extensionInfoService = extensionInfoService;
        this.restExtensionService = restExtensionService;
        this.fileService = fileService;
        this.extensionService = extensionService;
    }

    @GetMapping("api/extensions/approved")
    public List<ExtensionDTO> getAllApproved(){

        return restExtensionService.getAllApproved();

    }

    @GetMapping("api/extensions/{name}")
    public ExtensionDTO getExtensionByName(@PathVariable ("name") String name){

        return restExtensionService.getExtensionByName(name);
    }

    @GetMapping("api/extensions")
    public List<ExtensionDTO> getAllExtensions() throws RepositoryException {

        return restExtensionService.getAll();
    }

    @RequestMapping(value = "api/extensions/download/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody void downloadExtensionFile(HttpServletResponse response, @PathVariable("id")
            String id) throws IOException {

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        int fileId = extension.getUploadFile().getId();

        UploadFile uploadFile = fileService.getFile(fileId);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(uploadFile.getData());

        response.setHeader("Content-Disposition", "attachment; filename=" + uploadFile.getFileName());
        FileCopyUtils.copy(inputStream, response.getOutputStream());

        extensionService.registerDownload(extension);

    }
}
