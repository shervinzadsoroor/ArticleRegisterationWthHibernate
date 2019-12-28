package usecases.usecase;

import entities.User;

public interface CreateNewArticleUseCase {
    public void create(User user, String currentDate);
}
