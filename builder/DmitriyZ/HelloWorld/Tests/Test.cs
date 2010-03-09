using System;
using HelloLibrary;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests
{
    [TestClass]
    public class UnitTest
    {
        [TestMethod]
        public void TestArgumentNullException()
        {
            HelloLibrary.Library.HelloAll(null);
        }
    }
}