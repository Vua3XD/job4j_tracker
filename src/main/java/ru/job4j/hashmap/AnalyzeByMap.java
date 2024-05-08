package ru.job4j.hashmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double totalScore = 0;
        int totalSubjects = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                totalScore += subject.score();
                totalSubjects++;
            }
        }
        return totalScore / totalSubjects;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double pupilTotalScore = 0;
            for (Subject subject : pupil.subjects()) {
                pupilTotalScore += subject.score();
            }
            double averageScore = pupilTotalScore / pupil.subjects().size();
            result.add(new Label(pupil.name(), averageScore));
        }
        return result;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> subjectScores = new LinkedHashMap<>();
        Map<String, Integer> subjectCounts = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjectScores.merge(subject.name(), subject.score(), Integer::sum);
                subjectCounts.merge(subject.name(), 1, Integer::sum);
            }
        }
        List<Label> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : subjectScores.entrySet()) {
            double averageScore = (double) entry.getValue() / subjectCounts.get(entry.getKey());
            result.add(new Label(entry.getKey(), averageScore));
        }
        return result;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> studentScores = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double totalScore = 0;
            for (Subject subject : pupil.subjects()) {
                totalScore += subject.score();
            }
            studentScores.add(new Label(pupil.name(), totalScore));
        }
        studentScores.sort(Comparator.naturalOrder());
        return studentScores.get(studentScores.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> subjectScores = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjectScores.merge(subject.name(), subject.score(), Integer::sum);
            }
        }
        List<Label> subjectLabels = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : subjectScores.entrySet()) {
            subjectLabels.add(new Label(entry.getKey(), entry.getValue()));
        }
        subjectLabels.sort(Comparator.naturalOrder());
        return subjectLabels.get(subjectLabels.size() - 1);
    }
}