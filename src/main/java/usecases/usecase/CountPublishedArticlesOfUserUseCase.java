package usecases.usecase;

import entities.User;

public interface CountPublishedArticlesOfUserUseCase {
    public Long count(User user);
}
