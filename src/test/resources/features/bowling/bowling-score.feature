Feature: Bowling score

  Scenario: Compute score when all pins missed
    Given All pins missed
    When I compute the score
    Then The score is 0

  Scenario: Compute score when only one pin in first throw fell
    Given First pin of first throw fell
    When I compute the score
    Then The score is 1

  Scenario: Compute score when two pins in first throw fell
    Given Two pins of first throw fell
    When I compute the score
    Then The score is 2

  Scenario Template: Compute score when less than ten pins fell in the two first throws
    Given the first frame is "<first frame>"
    When I compute the score
    Then The score is <expected score>
    Examples:
      | first frame | expected score |
      | 2,1         | 3              |
      | 3,1         | 4              |
      | 3,2         | 5              |

  Scenario Template: Compute score when each frame is below ten
    Given the frames are "<pins>"
    When I compute the score
    Then The score is <expected score>
    Examples:
      | pins                                    | expected score |
      | 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0 | 2              |
      | 1,0,0,0,0,0,0,2,1,0,0,0,0,0,0,0,0,0,0,0 | 4              |
      | 1,0,0,0,1,0,0,2,1,0,0,0,0,0,0,0,0,0,0,0 | 5              |
      | 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 | 20              |

  Scenario Template: Compute score
    Given the frames are "<pins>"
    When I compute the score
    Then The score is <expected score>
    Examples: Compute score when there are spares
      | pins                                      | expected score |
      | 1,9,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0   | 12             |
      | 1,9,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0   | 14             |
      | 2,8,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0   | 15             |
      | 2,8,9,1,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0   | 35             |
      | 2,8,9,1,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0   | 37             |
      | 1,2,3,4,5,0,0,0,0,0,0,0,0,0,0,0,0,0,9,1,8 | 41             |
      | 1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1 | 111             |

    Examples: Compute score when there are strikes
      | pins                                      | expected score |
      | 10,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0    | 14             |
      | 10,5,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0    | 28             |
      | 0,0,10,5,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0    | 28             |
      | 0,0,0,0,10,5,4,0,0,0,0,0,0,0,0,0,0,0,0    | 28             |
      | 10,4,5,10,5,4,0,0,0,0,0,0,0,0,0,0,0,0     | 56             |
      | 10,0,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0     | 30             |
