Feature: Create task of a work day
  As a staff member 
  I want to create task of a work day
  So that I can create tasks

  Background: 
    Given I open browser and enter link website "https://qlsxpm.viettel.vn:9443/ccm/web"
    And I fill login form
      | username | password  |
      | hoangnl  | 123qwe!@# |
    And I login
@done
  Scenario Outline: Create work item success
    Given I go to current project "<curentProject>"
    #When I click plan menu
    #And I click to see all plans
    When I click all plan
    And I choose team "<team>"
    #Then I wait for completing task
    And I click the sprint has time "<dateOfSprint>"
   
    And I click add new work item
    And I click add new task
    And I enter with "<taskName>"
    #Then I wait to see detak task page
    And I go to detail task page
    And I enter time estimate "<timeEstimate>"
    And I enter due date "<dueDate>"
    And I click owned by

    And I click tab time tracking
    And I choose task group "<taskGroup>"
    And I click to add time entry row
    And I choose time code "<timeCode>"
    And I add time tracking with "<workHour>"
    
    #And I click save task
    #Then I wait for completing task
    #And I change status "START_WORKING"
    #And I click save task
    #And I change status "INVALIDATE"
    #And I click save task
    
    Examples: List of values
      | curentProject   						|team 									| dateOfSprint                | taskName | timeEstimate | workHour | dueDate       | taskGroup | timeCode |
      |  VTICT_VA_13001_HQMC (Change Management)|VTICT_VA_13001_HQMC (Change Management)| Nov 21, 2016 - Dec 24, 2016 | Test     |            0 |        0 | Nov 19, 2016 | Support   | Report   |
      |  VTICT_VA_13001_HQMC (Change Management)|VTICT_VA_13001_HQMC (Change Management)| Nov 21, 2016 - Dec 24, 2016 | Test1    |            0 |        0 | Nov 21, 2016 | Code   	| Document   |
      |  VTICT_VA_13001_HQMC (Change Management)|VTICT_VA_13001_HQMC (Change Management)| Nov 21, 2016 - Dec 24, 2016 | Test2    |            0 |        0 | Nov 22, 2016 | Support   | Execute   |

	@done	
   Scenario: Create work item success
    Given I go to current project "VTICT_VA_13001_HQMC (Change Management)"
    And I add work item
      | team                                    | sprintDate                  | taskName 									| timeEstimate 		| timeTracking 	| 	dueDate  	| taskGroup | timeCode |statusBefore |statusAfter|
      | VTICT_VA_13001_HQMC (Change Management) |  Nov 21, 2016 - Dec 24, 2016 | Hoàn thiện fix lỗi ATTT FCAP     				|      		 0  |        0 		| 19/11/2016 	| Support   | Report   |START_WORKING|INVALIDATE|
      | VTICT_VA_13001_HQMC (Change Management) |  Nov 21, 2016 - Dec 24, 2016 | Nâng cấp tính năng thủ tục Kiểm dịch thực vật    |    		 0 	|        0 		| 21/11/2016 	| Code      | Document |START_WORKING|INVALIDATE|
      | VTICT_VA_13001_HQMC (Change Management) |  Nov 21, 2016 - Dec 24, 2016 | Cài đặt môi trường phát triển thủ tục Kiểm dịch thực vật |   0 |        0 		| 22/11/2016 	| Support   | Execute  |START_WORKING|INVALIDATE|
  
  @done
  Scenario: Create work item success with upload excel
    Given I go to current project "VTICT_VA_13001_HQMC (Change Management)"
    And I upload excel
  
   Scenario: Create work item success with upload xml file
    Given I go to current project "VTICT_VA_13001_HQMC (Change Management)"
    And I upload xml file
  
  
  
  
  
  @wip
  Scenario: Login success to IBM
    And I login
    And I go to project dashboard page
    And I click plan menu
    And I click to see all plans
    And I select team area
    And I choose KDD team

  @wip
  Scenario Outline: Creat work item success
    And I go to project dashboard page
    And I click plan menu
    And I click to see all plans
    And I select team area
    And I choose a team
    And I click current sprint
    And I click add new work item
    And I click add new task
    And I enter with "<taskName>"
    And I go to detail task page
    And I enter time estimate "<timeEstimate>"
    And I enter due date "<duedate>"

    Examples: List of values
      | taskName          | timeEstimate | duedate                   |
      | This is task name |            8 | Nov 21, 2016, 12:00:00 PM |

 
  @wip
  Scenario Outline: Test click sprint as you want
    Given I go to current project "<curentProject>"
    When I click plan menu
    And I click to see all plans
    And I choose team "<team>"

 Examples: List of values
      | curentProject   						|team 									|
      |  VTICT_VA_13001_HQMC (Change Management)|VTICT_VA_13001_HQMC (Change Management)|
