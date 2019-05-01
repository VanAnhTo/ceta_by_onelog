package interfaces;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.detail.task.DashboardDetail;
import domain.detail.task.Task;

public class xmlDataSource implements externalDataSource {

	@Override
	public DashboardDetail readDashboardDetailFromExternalDatasource() throws Exception {
		DashboardDetail dashBoardDetail = new DashboardDetail();
		File fXmlFile = new File("D:/xml data/data.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("team");
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nTeamNode = nList.item(temp);
			if (nTeamNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nTeamNode;
				dashBoardDetail.setTeam(eElement.getElementsByTagName("teamName").item(0).getTextContent());

				NodeList nSprintList = eElement.getElementsByTagName("sprint");
				for (int i = 0; i < nSprintList.getLength(); i++) {
					Node nSprintNode = nSprintList.item(i);
					if (nSprintNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eTeamElement = (Element) nSprintNode;
						dashBoardDetail.setSprintDate(
								eTeamElement.getElementsByTagName("sprintName").item(0).getTextContent());
					}
				}
			}
		}
		return dashBoardDetail;
	}

	@Override
	public List<Task> readTaskListFromExternalDatasource() throws Exception {

		List<Task> listTask = new ArrayList<Task>();

		File fXmlFile = new File("D:/xml data/data.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		Task task = new Task();
		NodeList nTaskList = doc.getElementsByTagName("task");
		for (int j = 0; j < nTaskList.getLength(); j++) {

			Node nTaskNode = nTaskList.item(j);
			if (nTaskNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eTaskElement = (Element) nTaskNode;

				task.setTaskName(eTaskElement.getElementsByTagName("taskname").item(0).getTextContent());
				task.setTimeEstimate(eTaskElement.getElementsByTagName("timeestimate").item(0).getTextContent());
				task.setDueDate(eTaskElement.getElementsByTagName("duedate").item(0).getTextContent());
				task.setTaskGroup(eTaskElement.getElementsByTagName("taskgroup").item(0).getTextContent());
				task.setTimeCode(eTaskElement.getElementsByTagName("timecode").item(0).getTextContent());
				task.setTimeTracking(eTaskElement.getElementsByTagName("timetracking").item(0).getTextContent());
				task.setStatusBefore(eTaskElement.getElementsByTagName("statusbefore").item(0).getTextContent());
				task.setStatusAfter(eTaskElement.getElementsByTagName("statusafter").item(0).getTextContent());

				listTask.add(task);
			} 
		} 
		return listTask;
	}
}
