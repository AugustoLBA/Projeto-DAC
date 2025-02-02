package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.AuthorRequest;
import br.ifpb.dac.library_web.dto.AuthorResponse;
import br.ifpb.dac.library_web.entity.Author;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorMapper {
    public static Author toAuthor(AuthorRequest request) {
        if (request == null) {
            return null;
        }
        Author author = new Author();
        author.setName(request.getName());
        return author;
    }

    public static AuthorResponse toResponse(Author author) {
        if (author == null) {
            return null;
        }
        return new AuthorResponse(author.getId(), author.getName());
    }

    public static List<AuthorResponse> toResponseList(List<Author> authors) {
        if (authors == null) {
            return Collections.emptyList();
        }
        return authors.stream()
                .map(AuthorMapper::toResponse)
                .collect(Collectors.toList());
    }
}
