package br.com.gestao.gestao_estoque.controller;

import br.com.gestao.gestao_estoque.controller.dto.ProdutoRequest;
import br.com.gestao.gestao_estoque.controller.dto.ProdutoResponse;
import br.com.gestao.gestao_estoque.domain.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("/produtos")
@RestController
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(@Valid @RequestBody ProdutoRequest request,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        var response = service.criar(request);
        var uri = uriComponentsBuilder
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequest request) {
        var response = service.atualizar(id, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listar(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Boolean estoqueBaixo) {

        var responseList = service.listar(categoria, estoqueBaixo);

        return responseList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(responseList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
