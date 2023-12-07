package interface_adapter.Signup;

public class BackController {
    final BackPresenter backPresenter;
    public BackController(BackPresenter backPresenter) {
        this.backPresenter = backPresenter;
    }

    public void execute() {
        backPresenter.prepareSuccessView();
    }
}
