package usecases.usecase;

import entities.User;

public interface EditArticleUseCase {
    public void edit(Long id, User user, String currentDate);
}
