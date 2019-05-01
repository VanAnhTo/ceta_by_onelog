package util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.JavascriptExecutor;

import domain.builder.task.DashBoardBuilder;
import domain.builder.task.TaskDetailBuilder;
import domain.detail.account.LoginDetails;
import domain.detail.task.DashboardDetail;
import domain.detail.task.Task;
import domain.detail.task.TaskDetail;
import interfaces.excelDataSource;
import interfaces.externalDataSource;
import interfaces.xmlDataSource;
import page.LoginPage;
import page.ProjectDashBoardPage;
import page.TaskDetailPage;
import page.CurrentSprintPage;
import page.HomePage;

public class Specification {

	PageStore pageStore;
	private externalDataSource _source;
	private externalDataSource _xmlSource;

	public Specification(PageStore pageStore) {
		this.pageStore = pageStore;
		_source = new excelDataSource();
		_xmlSource = new xmlDataSource();
	}

	public void clickLoginWith(LoginDetails loginDetails) {
		LoginPage onLoginPage = pageStore.get(LoginPage.class);
		onLoginPage.loginToMainPage(loginDetails);
	}

	public void goToProjectDashboard() {
		HomePage onHomePage = pageStore.get(HomePage.class);
		onHomePage.goToProjectDashboard();
	}

	public void clickPlan() {
		ProjectDashBoardPage onProjectDashBoardPagee = pageStore.get(ProjectDashBoardPage.class);
		onProjectDashBoardPagee.clickPlan();
	}

	public void clickAddNewWorkItem() {
		CurrentSprintPage onCurrentSprintPage = pageStore.get(CurrentSprintPage.class);
		onCurrentSprintPage.clickDropDownAddWorkItem();
	}

	public void clickAddNewTask() {
		CurrentSprintPage onCurrentSprintPage = pageStore.get(CurrentSprintPage.class);
		onCurrentSprintPage.clickTaskOnDropDown();
	}

	public void goToDetailTaskPage(TaskDetail taskDetail) {
		CurrentSprintPage onCurrentSprintPage = pageStore.get(CurrentSprintPage.class);
		onCurrentSprintPage.gotoTaskDetailPageWithTaskName(taskDetail);
	}

	public void clickOwnedBy() {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.chooseOwnedBy();
	}

	public void enterTimeEstimate(TaskDetail taskDetail) {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.enterTimeEstimateWith(taskDetail);
	}

	public void clickTabTimeTracking() {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.clickTabTimeTracking();
	}

	public void chooseTaskGroup(TaskDetail taskDetail) {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.chooseTaskGroup(taskDetail.getTaskGroup());
	}

	public void clickToAddTimeEntryRow() {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.clickToAddTimeEntryRow();
	}

	public void enterTimeTracking(TaskDetail taskDetail) {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.enterTimeTracking(taskDetail.getTimeTracking());
	}

	public void enterDueDate(TaskDetail taskDetail) {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.enterDueDate(taskDetail.getDueDate());
	}

	public void clickSaveTask() {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.clickSaveTask();
	}

	public void clickSprint(DashboardDetail dashBoardDetail) {
		ProjectDashBoardPage onProjectDashBoardPagee = pageStore.get(ProjectDashBoardPage.class);
		onProjectDashBoardPagee.clickSprint(dashBoardDetail.getSprintDate());
	}

	public void chooseStatus(TaskDetail taskDetail) {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.chooseStatus(taskDetail.getStatus());
	}

	public void chooseStatusBefore(TaskDetail taskDetail) {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.chooseStatus(taskDetail.getStatusBefore());
	}

	public void chooseStatusAfter(TaskDetail taskDetail) {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.chooseStatus(taskDetail.getStatusAfter());
	}

	public void chooseTimeCode(TaskDetail taskDetail) {
		TaskDetailPage onTaskDetailPage = pageStore.get(TaskDetailPage.class);
		onTaskDetailPage.chooseTimeCode(taskDetail.getTimeCode());
	}

	public void chooseCurrentProject(DashboardDetail dashBoardDetail) {
		HomePage onHomePage = pageStore.get(HomePage.class);
		onHomePage.chooseProject(dashBoardDetail.getCurrentProject());
	}

	public void chooseTeam(DashboardDetail dashBoardDetail) {
		ProjectDashBoardPage onProjectDashBoardPagee = pageStore.get(ProjectDashBoardPage.class);
		onProjectDashBoardPagee.chooseTeam(dashBoardDetail.getTeam());
	}

	public void reloadPage() {
		ProjectDashBoardPage onProjectDashBoardPagee = pageStore.get(ProjectDashBoardPage.class);
		onProjectDashBoardPagee.reload();
	}

	public void addWorkItem(List<Task> tasks) throws ParseException {
		for (Task task : tasks) {
			DashBoardBuilder dashBoardBuilder = new DashBoardBuilder();
			dashBoardBuilder.withTeam(task.getTeam()).withSprintDate(task.getSprintDate());
			DashboardDetail dashBoardDetail = dashBoardBuilder.build();
			addWorkItemSteps(task, dashBoardDetail);

		}

	}

	public void uploadExel() throws Exception {
		DashboardDetail dashBoardDetail = _source.readDashboardDetailFromExternalDatasource();
		List<Task> listTask = _source.readTaskListFromExternalDatasource();
		System.out.println("A");
		for (int indexR = 1; indexR < listTask.size(); indexR++) {
			addWorkItemSteps(listTask.get(indexR), dashBoardDetail);
		}
	}
	
	public void uploadXmlFile() throws Exception {
		List<Task> listTask = _xmlSource.readTaskListFromExternalDatasource();
		DashboardDetail dashBoardDetail =  _xmlSource.readDashboardDetailFromExternalDatasource();
		
		for (int indexR = 1; indexR < listTask.size(); indexR++) {
			addWorkItemSteps(listTask.get(indexR), dashBoardDetail);
		}
	}

	private void addWorkItemSteps(Task task, DashboardDetail dashBoardDetail) {
		TaskDetailBuilder taskBuilder = new TaskDetailBuilder();
		taskBuilder.withTaskName(task.getTaskName()).withTimeEstimate(task.getTimeEstimate())
				.withDueDate(task.getDueDate()).withStatusBefore(task.getStatusBefore())
				.withTaskGroup(task.getTaskGroup()).withTimeCode(task.getTimeCode())
				.withTimeTracking(task.getTimeTracking()).withStatusAfter(task.getStatusAfter());
		TaskDetail taskDetail = taskBuilder.build() ;

		this.clickPlan();
		this.chooseTeam(dashBoardDetail);
		this.clickSprint(dashBoardDetail);
		this.clickAddNewWorkItem();
		this.clickAddNewTask();
		this.goToDetailTaskPage(taskDetail);
		this.enterTimeEstimate(taskDetail);
		this.enterDueDate(taskDetail);
		this.clickOwnedBy();
		this.clickTabTimeTracking();
		this.chooseTaskGroup(taskDetail);
		this.clickToAddTimeEntryRow();
		this.chooseTimeCode(taskDetail);
		this.enterTimeTracking(taskDetail);
		this.clickSaveTask();
		this.chooseStatusBefore(taskDetail);
		this.clickSaveTask();
		this.chooseStatusAfter(taskDetail);
		this.clickSaveTask();
		this.reloadPage();
	}

}
