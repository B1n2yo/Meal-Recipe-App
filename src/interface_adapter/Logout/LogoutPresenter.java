package interface_adapter.Logout;

import interface_adapter.ViewManagerModel;
import use_case.Logout.LogoutOutputBoundary;

public class LogoutPresenter implements LogoutOutputBoundary {
    private final LogoutViewModel logoutViewModel;
    private ViewManagerModel viewManagerModel;
    public LogoutPresenter(LogoutViewModel logoutViewModel, ViewManagerModel viewManagerModel) {
        this.logoutViewModel = logoutViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(this.logoutViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
