package usecases.usecase;

import entities.User;

public interface PublishArticleUseCase {
    public void publish(Long id, User user, String currentDate);
}
