package com.example.isportshop

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    RegisterActivityTest::class
)
class ActivityTestSuite