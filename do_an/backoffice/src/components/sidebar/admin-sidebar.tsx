import {
  SideBar,
  SideBarGroupDropdown,
  SideBarGroupDropdownTrigger,
  SideBarGroupDropdownContent,
  SideBarLink,
} from "@/components/ui/sidebar";
import { TbLayoutDashboard } from "react-icons/tb";
import {
  PiBook,
  PiBookBookmarkFill,
  PiBookmarks,
  PiBooks,
  PiChalkboardTeacher,
  PiExam,
  PiExamFill,
  PiStudent,
  PiUserFill,
} from "react-icons/pi";
import { HiDocumentReport } from "react-icons/hi";
import { AiOutlineSchedule } from "react-icons/ai";
import {
  GrAdd,
  GrAnalytics,
  GrDocumentPerformance,
  GrShieldSecurity,
  GrUserAdmin,
} from "react-icons/gr";
import { FcStatistics } from "react-icons/fc";
import { RiAccountBoxLine, RiSettings3Fill } from "react-icons/ri";
import { IoHelpCircleOutline, IoSettingsOutline } from "react-icons/io5";

export function AdminSideBar() {
  return (
    <SideBar>
      <SideBarLink href="/admin/dashboard">
        <TbLayoutDashboard /> Dashboard
      </SideBarLink>
      <SideBarGroupDropdown>
        <SideBarGroupDropdownTrigger>
          <PiUserFill />
          Users
        </SideBarGroupDropdownTrigger>
        <SideBarGroupDropdownContent>
          <SideBarLink href="/admin/users/students">
            <PiStudent /> Students
          </SideBarLink>
          <SideBarLink href="/admin/users/instructors">
            <PiChalkboardTeacher />
            Instructors
          </SideBarLink>
          <SideBarLink href="/admin/users/admins">
            <GrUserAdmin />
            Admins
          </SideBarLink>
        </SideBarGroupDropdownContent>
      </SideBarGroupDropdown>
      <SideBarGroupDropdown>
        <SideBarGroupDropdownTrigger>
          <PiBookBookmarkFill />
          Courses
        </SideBarGroupDropdownTrigger>
        <SideBarGroupDropdownContent>
          <SideBarLink href="/admin/courses/manage">
            <PiBooks />
            Manage Course
          </SideBarLink>
          <SideBarLink href="/admin/courses/add">
            <PiBook />
            Add Course
          </SideBarLink>
          <SideBarLink href="/admin/courses/categories">
            <PiBookmarks />
            Categories
          </SideBarLink>
        </SideBarGroupDropdownContent>
      </SideBarGroupDropdown>
      <SideBarGroupDropdown>
        <SideBarGroupDropdownTrigger>
          <PiExamFill />
          Exams
        </SideBarGroupDropdownTrigger>
        <SideBarGroupDropdownContent>
          <SideBarLink href="/admin/exams/scheduled">
            <AiOutlineSchedule />
            Scheduled Exams
          </SideBarLink>
          <SideBarLink href="/admin/exams/create">
            <GrAdd />
            Create New Exam
          </SideBarLink>
          <SideBarLink href="/admin/exams/results">
            <PiExam />
            Results
          </SideBarLink>
        </SideBarGroupDropdownContent>
      </SideBarGroupDropdown>

      <SideBarGroupDropdown>
        <SideBarGroupDropdownTrigger>
          <HiDocumentReport />
          Reports
        </SideBarGroupDropdownTrigger>
        <SideBarGroupDropdownContent>
          <SideBarLink href="/admin/reports/student-performance">
            <GrDocumentPerformance />
            Student Performance
          </SideBarLink>
          <SideBarLink href="/admin/reports/course-statistics">
            <FcStatistics />
            Course Statistics
          </SideBarLink>
          <SideBarLink href="/admin/reports/exam-analysis">
            <GrAnalytics />
            Exam Analysis
          </SideBarLink>
        </SideBarGroupDropdownContent>
      </SideBarGroupDropdown>

      <SideBarGroupDropdown>
        <SideBarGroupDropdownTrigger>
          <RiSettings3Fill />
          Settings
        </SideBarGroupDropdownTrigger>
        <SideBarGroupDropdownContent>
          <SideBarLink href="/admin/settings/general">
            <IoSettingsOutline />
            General Settings
          </SideBarLink>
          <SideBarLink href="/admin/settings/account">
            <RiAccountBoxLine />
            Account Settings
          </SideBarLink>
          <SideBarLink href="/admin/settings/security">
            <GrShieldSecurity />
            Security
          </SideBarLink>
        </SideBarGroupDropdownContent>
      </SideBarGroupDropdown>

      <SideBarLink href="/admin/help">
			<IoHelpCircleOutline />
        Help
      </SideBarLink>
    </SideBar>
  );
}
