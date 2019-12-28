package usecases.usecase;

import entities.User;

public interface DeleteArticleUseCase {
    public void delete(Long id, User user);
}
