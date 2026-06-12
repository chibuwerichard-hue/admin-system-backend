package com.school.admin_system.service;
import com.school.admin_system.entity.Attendance;
import com.school.admin_system.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public Optional<Attendance> findById(Long id) {
        return attendanceRepository.findById(id);
    }

    public List<Attendance> findByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public List<Attendance> findByDateRange(LocalDate start, LocalDate end) {
        return attendanceRepository.findByAttendanceDateBetween(start, end);
    }

    public Attendance updateAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}
