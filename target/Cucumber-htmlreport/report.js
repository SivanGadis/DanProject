$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("API_TEST/Test/OLD/Refactoring.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#Author: tommy.berman@qualitestgroup.com"
    },
    {
      "line": 2,
      "value": "#Keywords Summary : DAN HOTEL, SignUp, SendOTP, CheckOTP, Sign In ,SignOUT, LinkReservation, Unlink Reservation."
    }
  ],
  "line": 5,
  "name": "Flow Test for Ten API of Dan Hotel",
  "description": "",
  "id": "flow-test-for-ten-api-of-dan-hotel",
  "keyword": "Feature",
  "tags": [
    {
      "line": 4,
      "name": "@Test11111"
    }
  ]
});
formatter.scenario({
  "comments": [
    {
      "line": 7,
      "value": "#  @tag1"
    }
  ],
  "line": 8,
  "name": "All Tests for QA Developr",
  "description": "",
  "id": "flow-test-for-ten-api-of-dan-hotel;all-tests-for-qa-developr",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "comments": [
    {
      "line": 10,
      "value": "#Given I SignUp new user with random email"
    },
    {
      "line": 12,
      "value": "#When I SignUp new user for email \"dantest4@mailinator.com\""
    }
  ],
  "line": 14,
  "name": "I send OTP for email \"dantest4@mailinator.com\"",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "I wait for \"4000\" milliseconds",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "I go to mailinator and get OTP for email \"https://www.mailinator.com/v4/public/inboxes.jsp?to\u003ddantest4\"",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "I check OTP for Token with email \"dantest4@mailinator.com\"",
  "keyword": "Then "
});
formatter.step({
  "line": 22,
  "name": "Update User Profile and Attach User Picture",
  "keyword": "Given "
});
formatter.step({
  "line": 24,
  "name": "open excel file from config \"ExcelFile\"",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "I wait for \"3000\" milliseconds",
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "I get value from excel for \"14004\"",
  "keyword": "And "
});
formatter.step({
  "comments": [
    {
      "line": 29,
      "value": "#And I get value from excel for \"Autostar1711\""
    }
  ],
  "line": 31,
  "name": "I wait for \"3000\" milliseconds",
  "keyword": "And "
});
formatter.step({
  "line": 33,
  "name": "I prepare reservation data by \"number\"",
  "keyword": "When "
});
formatter.step({
  "line": 35,
  "name": "I choose API action \"post\" and send response",
  "keyword": "Then "
});
formatter.step({
  "comments": [
    {
      "line": 37,
      "value": "#Then I unlink reservation with reservation ID"
    }
  ],
  "line": 39,
  "name": "I Sign out",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "dantest4@mailinator.com",
      "offset": 22
    }
  ],
  "location": "Steps.I_send_OTP_for_email(String)"
});
formatter.result({
  "duration": 2935949300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "4000",
      "offset": 12
    }
  ],
  "location": "Steps.i_wait_for_x_milliseconds(String)"
});
formatter.result({
  "duration": 4000778800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "https://www.mailinator.com/v4/public/inboxes.jsp?to\u003ddantest4",
      "offset": 42
    }
  ],
  "location": "Steps.I_go_to_mailinator_and_get_OTP(String)"
});
formatter.result({
  "duration": 4902410500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "dantest4@mailinator.com",
      "offset": 34
    }
  ],
  "location": "Steps.I_check_OTP_for_Token_with_email(String)"
});
formatter.result({
  "duration": 9752157200,
  "status": "passed"
});
formatter.match({
  "location": "Steps.Update_User_Profile_and_Attach_User_Picture()"
});
formatter.result({
  "duration": 210283500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "ExcelFile",
      "offset": 29
    }
  ],
  "location": "Steps.open_excel_file_from_config(String)"
});
formatter.result({
  "duration": 820500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3000",
      "offset": 12
    }
  ],
  "location": "Steps.i_wait_for_x_milliseconds(String)"
});
formatter.result({
  "duration": 3000680900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "14004",
      "offset": 28
    }
  ],
  "location": "Steps.I_get_value_from_excel_by(String)"
});
formatter.result({
  "duration": 1186060000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3000",
      "offset": 12
    }
  ],
  "location": "Steps.i_wait_for_x_milliseconds(String)"
});
formatter.result({
  "duration": 3002071200,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "number",
      "offset": 31
    }
  ],
  "location": "Steps.I_Prepare_reservation_data_by(String)"
});
formatter.result({
  "duration": 198200,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "post",
      "offset": 21
    }
  ],
  "location": "Steps.I_choose_API_action_and_send_response(String)"
});
formatter.result({
  "duration": 283095900,
  "error_message": "java.lang.AssertionError: Error: Reservation has an incorrect state to be linked\r\n\tat stepDefinition.Steps.I_choose_API_action_and_send_response(Steps.java:753)\r\n\tat ✽.Then I choose API action \"post\" and send response(API_TEST/Test/OLD/Refactoring.feature:35)\r\n",
  "status": "failed"
});
formatter.match({
  "location": "Steps.I_Sign_out()"
});
formatter.result({
  "status": "skipped"
});
});