package usecases.usecase;

import entities.User;

public interface CountAllArticlesOfUserUseCase {
    public Long count(User user);
}
